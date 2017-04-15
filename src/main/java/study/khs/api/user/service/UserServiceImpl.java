package study.khs.api.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.message.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.exception.UserLoginException;
import study.khs.api.user.repository.UserRepository;
import study.khs.common.dto.UserJoinRequestDto;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

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
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("userNickname", user.getUserNickname());
			// 토큰 만들어서 반납
		}

		return null;
	}

}
