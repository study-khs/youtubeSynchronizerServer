package study.khs.api.user.service;

import study.khs.api.message.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.common.dto.UserJoinRequestDto;

public interface UserService {

	User createUser(UserJoinRequestDto userJoinRequestDto);

	AuthorizationTokenDto login(UserType userType, String userLoginId, String userPassword);
}
