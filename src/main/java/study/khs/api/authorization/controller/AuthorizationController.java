package study.khs.api.authorization.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import study.khs.api.authorization.dto.UserJoinRequestDto;
import study.khs.api.authorization.exception.UserJoinException;
import study.khs.api.channel.dto.ChannelInfoDto;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.dto.AuthorizationTokenDto;
import study.khs.api.user.dto.UserInfoDto;
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
	@RequestMapping(value = "/login-with-facebook", method = RequestMethod.GET)
	public ApiResponseDto<AuthorizationTokenDto> userLoginWithFacebook(@RequestHeader UserType userType,
			@RequestHeader String token) {

		log.info("userLoginWithFacebook userType=[{}] token=[{}]", userType, token);

		if (!UserType.FACEBOOK.equals(userType)) {
			throw new UserJoinException();
		}

		AuthorizationTokenDto authorizationTokenDto = userService.loginWithFacebook(token);
		// AuthorizationTokenDto authorizationTokenDto = new
		// AuthorizationTokenDto();

		ApiResponseDto<AuthorizationTokenDto> responseDto = //
				ApiResponseDto.<AuthorizationTokenDto>builder()//
						.data(authorizationTokenDto)//
						.build();

		log.info("userLogin responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "네이버 유저 로그인하기", notes = "네이버 유저 로그인하기")
	@RequestMapping(value = "/login-with-naver", method = RequestMethod.GET)
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

	@ApiOperation(value = "임시로 쓰는 채널 API 더미", notes = "임시로 쓰는 채널 API 더미")
	@RequestMapping(value = "/dummy-channel", method = RequestMethod.GET)
	public ApiResponseDto<ChannelInfoDto> dummyChannel() {

		ChannelInfoDto channelInfoDto = new ChannelInfoDto();
		channelInfoDto.setChannelId(1234567890L);
		UserInfoDto channelManager = new UserInfoDto();
		channelManager.setUserId(1L);
		channelManager.setUserNickname("박정성방장");
		channelInfoDto.setChannelManager(channelManager);
		List<String> youtubeUrlList = new ArrayList<String>();
		youtubeUrlList.add("https://www.youtube.com/watch?v=0rtV5esQT6I");
		youtubeUrlList.add("https://www.youtube.com/watch?v=QslJYDX3o8s");
		channelInfoDto.setYoutubeUrlList(youtubeUrlList);
		ArrayList<UserInfoDto> memberList = new ArrayList<UserInfoDto>();
		UserInfoDto member1 = new UserInfoDto();
		member1.setUserId(2L);
		member1.setUserNickname("박재영멤버");
		memberList.add(member1);
		UserInfoDto member2 = new UserInfoDto();
		member2.setUserId(3L);
		member2.setUserNickname("김영재멤버");
		memberList.add(member2);
		UserInfoDto member3 = new UserInfoDto();
		member3.setUserId(4L);
		member3.setUserNickname("김성식멤버");
		memberList.add(member3);
		channelInfoDto.setMemberList(memberList);
		channelInfoDto.setActivated(true);

		ApiResponseDto<ChannelInfoDto> responseDto = //
				ApiResponseDto.<ChannelInfoDto>builder()//
						.data(channelInfoDto)//
						.build();

		return responseDto;
	}
}
