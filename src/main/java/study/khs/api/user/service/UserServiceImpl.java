package study.khs.api.user.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.dto.UserJoinRequestDto;
import study.khs.api.user.dto.UserUpdateRequestDto;
import study.khs.api.user.exception.UserAlreadyExistException;
import study.khs.api.user.exception.UserNotExistException;
import study.khs.api.user.exception.WrongPasswordException;
import study.khs.api.user.repository.UserRepository;
import study.khs.common.exception.BaseException;
import study.khs.common.exception.InvalidAccessException;
import study.khs.common.exception.InvalidFormException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TextEncryptor textEncryptor;

	@Override
	public User createUser(UserJoinRequestDto userJoinRequestDto) {

		log.info("createUser userJoinRequestDto=[{}]", userJoinRequestDto);

		User user = userRepository.findOneByUserTypeAndUserLoginId(userJoinRequestDto.getUserType().getCode(),
				userJoinRequestDto.getUserLoginId());

		if (user != null) {
			throw new UserAlreadyExistException();
		}

		String encodePassword = passwordEncoder.encode(userJoinRequestDto.getUserPassword());
		userJoinRequestDto.setUserPassword(encodePassword);

		User createdUser = userRepository.save(new User(userJoinRequestDto));

		log.info("createUser createdUser=[{}]", createdUser);

		return createdUser;
	}

	@Override
	public User createUserWithSns(UserType userType, String userLoginId, String userNickname) {

		log.info("createUserWithSns userType=[{}], userLoginId=[{}], userNickname=[{}]", userType, userLoginId,
				userNickname);

		User createdUser = userRepository.save(new User(userType, userLoginId, userNickname));

		log.info("createUserWithSns createdUser=[{}]", createdUser);

		return createdUser;
	}

	@Override
	public AuthorizationTokenDto login(UserType userType, String userLoginId, String userPassword) {

		log.info("login userType=[{}], userLoginId=[{}], userPassword=[{}]", userType, userLoginId, userPassword);

		User user = userRepository.findOneByUserTypeAndUserLoginId(userType.getCode(), userLoginId);

		if (user == null) {
			throw new UserNotExistException();
		} else if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
			throw new WrongPasswordException();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		map.put("userType", user.getUserType());
		map.put("userLoginId", user.getUserLoginId());
		map.put("userNickname", user.getUserNickname());

		String authorizationToken = null;
		try {
			authorizationToken = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new BaseException();
		}
		authorizationToken = textEncryptor.encrypt(authorizationToken);

		AuthorizationTokenDto authorizationTokenDto = new AuthorizationTokenDto();
		authorizationTokenDto.setAuthorizationToken(authorizationToken);

		log.info("login tokenDto=[{}]", authorizationTokenDto);

		return authorizationTokenDto;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AuthorizationTokenDto loginWithSns(UserType userType, String token) {

		log.info("logloginWithSnsin userType=[{}], token=[{}]", userType, token);

		HttpHeaders httpHeaders;
		HttpEntity<String> httpEntity;
		URI uri;
		ResponseEntity<HashMap> responseEntity;
		Map<?, ?> body;
		String userLoginId, userNickname;

		switch (userType) {
		case FACEBOOK:
			httpHeaders = new HttpHeaders();
			httpEntity = new HttpEntity<>(httpHeaders);
			uri = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/me").queryParam("access_token", token)
					.build().encode().toUri();
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, HashMap.class);
			if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
				throw new InvalidAccessException();
			}
			body = responseEntity.getBody();
			userLoginId = (String) body.get("id");
			userNickname = (String) body.get("name");
			break;
		case NAVER:
			httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer ".concat(token));
			httpEntity = new HttpEntity<>(httpHeaders);
			uri = UriComponentsBuilder.fromHttpUrl("https://openapi.naver.com/v1/nid/me").build().encode().toUri();
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, HashMap.class);
			if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
				throw new InvalidAccessException();
			}
			body = responseEntity.getBody();
			Map response = (Map) body.get("response");
			userLoginId = (String) response.get("id");
			userNickname = (String) response.get("name");
			break;
		default:
			throw new InvalidFormException();
		}

		User user = userRepository.findOneByUserTypeAndUserLoginId(userType.getCode(), userLoginId);
		if (user == null) {
			user = createUserWithSns(userType, userLoginId, userNickname);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		map.put("userType", user.getUserType());
		map.put("userLoginId", user.getUserLoginId());
		map.put("userNickname", user.getUserNickname());

		String authorizationToken = null;
		try {
			authorizationToken = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new BaseException();
		}
		authorizationToken = textEncryptor.encrypt(authorizationToken);

		AuthorizationTokenDto authorizationTokenDto = new AuthorizationTokenDto();
		authorizationTokenDto.setAuthorizationToken(authorizationToken);

		log.info("loginWithSns tokenDto=[{}]", authorizationTokenDto);

		return authorizationTokenDto;
	}

	public User getUser(Long userId) {

		log.info("getUser userId=[{}]", userId);

		User user = userRepository.findOne(userId);

		log.info("getUser user=[{}]", user);

		return user;
	}

	public User updateUser(UserUpdateRequestDto userUpdateRequestDto) {

		log.info("updateUser userUpdateRequestDto=[{}]", userUpdateRequestDto);

		User user = userRepository.findOne(userUpdateRequestDto.getUserId());

		if (user == null) {
			log.info("updateUser user not exist userId=[{}]", userUpdateRequestDto.getUserId());
			throw new UserNotExistException();
		}

		user.setUserNickname(userUpdateRequestDto.getUserNickname());
		User savedUser = userRepository.save(user);

		log.info("updateUser savedUser=[{}]", savedUser);

		return savedUser;
	}

	public void deleteUser(Long userId) {

		log.info("deleteUser userId=[{}]", userId);

		if (!userRepository.exists(userId)) {
			log.info("deleteUser user not exist userId=[{}]", userId);
			throw new UserNotExistException();
		}

		userRepository.delete(userId);
		return;
	}
}
