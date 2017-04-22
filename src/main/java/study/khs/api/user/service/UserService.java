package study.khs.api.user.service;

import study.khs.api.authorization.dto.UserJoinRequestDto;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.dto.AuthorizationTokenDto;

public interface UserService {

	User createUser(UserJoinRequestDto userJoinRequestDto);

	AuthorizationTokenDto login(UserType userType, String userLoginId, String userPassword);

	AuthorizationTokenDto loginWithFacebook(String token);
}
