package study.khs.api.user.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.authorization.dto.UserJoinRequestDto;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.exception.UserLoginException;
import study.khs.api.user.repository.UserRepository;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public User createUser(UserJoinRequestDto userJoinRequestDto) {

		log.info("createUser userJoinRequestDto=[{}]", userJoinRequestDto);

		User createdUser = userRepository.save(new User(userJoinRequestDto));

		log.info("createUser createdUser=[{}]", createdUser);

		return createdUser;
	}

	@Override
	public AuthorizationTokenDto login(UserType userType, String userLoginId, String userPassword) {

		User user = userRepository.findOneByUserTypeAndUserLoginId(userType.getCode(), userLoginId);

		if (user == null || !user.getUserPassword().equals(userPassword)) {
			throw new UserLoginException();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		map.put("userNickname", user.getUserNickname());
		// 토큰 만들어서 반납

		return null;
	}

	@Override
	public AuthorizationTokenDto loginWithFacebook(String token) {

		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

		URI uri = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/me").queryParam("fileds", "id,name")
				.queryParam("access_token",
						"EAABisr2Aeb8BAA77QckYu4YM5ZBOvRUXm3hNThFTceCnNrhbOjqIxWZASDLbrsJmo5kdcwZBlwomRd1wBJOMMvZBElFAnmIU5OMedNd8p1ZCoAjUuHoYLj1K2Tfl4UrWkwp1hiZBjoAeZAIxbF1K9jgM5lO7C39n9A4iAYQcE4xSLZBp7OmxElZCEvLX1b9AesTvueObaA7QYckCLwBVQ8SZCTKDb7bifVmvsZD")
				.build().encode().toUri();

		Map<?, ?> body = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, HashMap.class).getBody();
		String authorizationToken = null;
		try {
			authorizationToken = objectMapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AuthorizationTokenDto tokenDto = new AuthorizationTokenDto();
		tokenDto.setAuthorizationToken(authorizationToken);
		return tokenDto;
	}
}
