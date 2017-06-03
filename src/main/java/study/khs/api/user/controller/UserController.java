package study.khs.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.domain.UserInfo;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.dto.UserJoinRequestDto;
import study.khs.api.user.service.UserService;
import study.khs.common.constants.ValueConstants;
import study.khs.common.dto.ApiResponseDto;
import study.khs.common.exception.InvalidFormException;

/**
 * UserController
 * 
 * @author JSPark
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "일반 유저 가입", notes = "일반 유저 가입")
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ApiResponseDto<Object> userJoin(@RequestBody UserJoinRequestDto userJoinRequestDto) {

		log.info("userJoin userJoinRequestDto=[{}]", userJoinRequestDto);

		if (!UserType.STANDARD.equals(userJoinRequestDto.getUserType())) {
			throw new InvalidFormException();
		}

		userService.createUser(userJoinRequestDto);

		ApiResponseDto<Object> responseDto = //
				ApiResponseDto.<Object>builder()//
						.data(null)//
						.build();

		log.info("userJoin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "일반 유저 로그인", notes = "일반 유저 로그인")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLogin(@RequestHeader UserType userType,
			@RequestHeader String userLoginId, @RequestHeader String userPassword) {

		log.info("userLogin userType=[{}] userLoginId=[{}] userPassword=[{}]", userType, userLoginId, userPassword);

		if (!UserType.STANDARD.equals(userType)) {
			throw new InvalidFormException();
		}

		AuthorizationTokenDto authorizationTokenDto = userService.login(userType, userLoginId, userPassword);

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "페이스북 유저 로그인", notes = "페이스북 유저 로그인")
	@RequestMapping(value = "/login-with-facebook", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLoginWithFacebook(@RequestHeader UserType userType,
			@RequestHeader String token) {

		log.info("userLoginWithFacebook userType=[{}] token=[{}]", userType, token);

		if (!UserType.FACEBOOK.equals(userType)) {
			throw new InvalidFormException();
		}

		AuthorizationTokenDto authorizationTokenDto = userService.loginWithSns(userType, token);

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "네이버 유저 로그인", notes = "네이버 유저 로그인")
	@RequestMapping(value = "/login-with-naver", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLoginWithNaver(@RequestHeader UserType userType,
			@RequestHeader String token) {

		log.info("userLoginWithNaver userType=[{}] token=[{}]", userType, token);

		if (!UserType.NAVER.equals(userType)) {
			throw new InvalidFormException();
		}

		AuthorizationTokenDto authorizationTokenDto = userService.loginWithSns(userType, token);

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "유저 정보 조회", notes = "유저 정보 조회")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ApiResponseDto<UserInfo> viewUser(@ApiParam @PathVariable Long userId,
			@ApiParam @RequestHeader(value = ValueConstants.AUTHORIZATION_TOKEN_NAME) String authorizationToken,
			UserInfo userInfo) {

		log.info("viewUser userInfo=[{}]", userInfo);

		UserInfo targetUserInfo = userService.getUserInfo(userId);

		ApiResponseDto<UserInfo> responseDto = //
				ApiResponseDto.<UserInfo>builder()//
						.data(targetUserInfo)//
						.build();

		log.info("viewUser responseDto=[{}]", responseDto);

		return responseDto;
	}
}
