package study.khs.api.user.service;

import study.khs.api.user.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.domain.UserInfo;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.dto.UserJoinRequestDto;

public interface UserService {

	User createUser(UserJoinRequestDto userJoinRequestDto);

	User createUserWithSns(UserType userType, String userLoginId, String userNickname);

	AuthorizationTokenDto login(UserType userType, String userLoginId, String userPassword);

	AuthorizationTokenDto loginWithSns(UserType userType, String token);

	UserInfo getUserInfo(Long userId);
}
