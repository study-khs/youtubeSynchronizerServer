package study.khs.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import study.khs.api.message.constants.UserType;
import study.khs.api.user.domain.User;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.service.UserService;
import study.khs.common.dto.ApiResponseDto;
import study.khs.common.dto.UserJoinRequestDto;
import study.khs.web.spring.aop.annotation.WithoutAuthorization;

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

	@ApiOperation(value = "유저 가입하기", notes = "유저 가입하기")
	@WithoutAuthorization
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ApiResponseDto<User> userJoin(UserJoinRequestDto userJoinRequestDto) {

		log.info("userJoin userJoinRequestDto=[{}]", userJoinRequestDto);

		User user = userService.createUser(userJoinRequestDto);

		ApiResponseDto<User> responseDto = //
				ApiResponseDto.<User>builder()//
						.data(user)//
						.build();

		log.info("userJoin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "유저 로그인하기", notes = "유저 로그인하기")
	@WithoutAuthorization
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLogin(@RequestHeader UserType userType,
			@RequestHeader String userLoginId, @RequestHeader String userPassword) {

		log.info("userLogin userType=[{}] userLoginId=[{}] userPassword=[{}]", userType, userLoginId, userPassword);

		AuthorizationTokenDto token = userService.login(userType, userLoginId, userPassword);

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(token)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

}
