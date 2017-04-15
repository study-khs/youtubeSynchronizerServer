package study.khs.api.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import study.khs.api.authorization.dto.UserJoinRequestDto;
import study.khs.api.authorization.exception.UserJoinException;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.service.UserService;
import study.khs.common.dto.ApiResponseDto;

/**
 * AuthorizationController
 * 
 * @author JSPark
 *
 */
@Slf4j
@RestController
@RequestMapping("")
public class AuthorizationController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "일반 유저 가입하기", notes = "일반 유저 가입하기")
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ApiResponseDto<Object> userJoin(UserJoinRequestDto userJoinRequestDto) {

		log.info("userJoin userJoinRequestDto=[{}]", userJoinRequestDto);

		if (!UserType.STANDARD.equals(userJoinRequestDto.getUserType())) {
			throw new UserJoinException();
		}

		userService.createUser(userJoinRequestDto);

		ApiResponseDto<Object> responseDto = //
				ApiResponseDto.<Object>builder()//
						.data(null)//
						.build();

		log.info("userJoin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "일반 유저 로그인하기", notes = "일반 유저 로그인하기")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLogin(@RequestHeader UserType userType,
			@RequestHeader String userLoginId, @RequestHeader String userPassword) {

		log.info("userLogin userType=[{}] userLoginId=[{}] userPassword=[{}]", userType, userLoginId, userPassword);

		if (!UserType.STANDARD.equals(userType)) {
			throw new UserJoinException();
		}

		AuthorizationTokenDto authorizationTokenDto = userService.login(userType, userLoginId, userPassword);

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "페이스북 유저 로그인하기", notes = "페이스북 유저 로그인하기")
	@RequestMapping(value = "/loginWithFacebook", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLoginWithFacebook(@RequestHeader UserType userType,
			@RequestHeader String token) {

		log.info("userLoginWithFacebook userType=[{}] token=[{}]", userType, token);

		if (!UserType.FACEBOOK.equals(userType)) {
			throw new UserJoinException();
		}

		// AuthorizationTokenDto authorizationTokenDto =
		// userService.loginWithFacebook(token);
		AuthorizationTokenDto authorizationTokenDto = new AuthorizationTokenDto();

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "네이버 유저 로그인하기", notes = "네이버 유저 로그인하기")
	@RequestMapping(value = "/loginWithNaver", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLoginWithNaver(@RequestHeader UserType userType,
			@RequestHeader String token) {

		log.info("userLoginWithNaver userType=[{}] token=[{}]", userType, token);

		if (!UserType.NAVER.equals(userType)) {
			throw new UserJoinException();
		}

		// AuthorizationTokenDto authorizationTokenDto =
		// userService.loginWithNaver(token);
		AuthorizationTokenDto authorizationTokenDto = new AuthorizationTokenDto();

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}
}
