package study.khs.api.channel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;
import study.khs.api.channel.domain.Channel;
import study.khs.api.channel.dto.ChannelCreateRequestDto;
import study.khs.api.channel.dto.VideoAddRequestDto;
import study.khs.api.channel.service.ChannelService;
import study.khs.api.user.domain.UserInfo;
import study.khs.common.constants.ValueConstants;
import study.khs.common.dto.ApiResponseDto;
import study.khs.common.dto.PageDto;

@Slf4j
@RestController
@RequestMapping("/api/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	@ApiOperation(value = "채널 생성", notes = "채널 생성")
	@RequestMapping(method = RequestMethod.POST)
	public ApiResponseDto<Channel> createChannel(
			@RequestHeader(value = ValueConstants.AUTHORIZATION_TOKEN_NAME) String authorizationToken,
			@RequestBody ChannelCreateRequestDto channelCreateRequestDto, @ApiIgnore UserInfo userInfo) {

		channelCreateRequestDto.setChannelManagerId(userInfo.getUserId());

		Channel channel = channelService.createChannel(channelCreateRequestDto);

		ApiResponseDto<Channel> responseDto = //
				ApiResponseDto.<Channel>builder()//
						.data(channel)//
						.build();

		log.info("createChannel responseDto=[{}]", responseDto);

		return responseDto;
	}

	@ApiOperation(value = "채널 목록 조회", notes = "채널 목록 조회")
	@RequestMapping(method = RequestMethod.GET)
	public ApiResponseDto<PageDto<Channel>> viewChannelList(
			@RequestHeader(value = ValueConstants.AUTHORIZATION_TOKEN_NAME) String authorizationToken,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

		PageDto<Channel> pageDto = channelService.viewChannelList(page, size);

		ApiResponseDto<PageDto<Channel>> responseDto = //
				ApiResponseDto.<PageDto<Channel>>builder()//
						.data(pageDto)//
						.build();

		log.info("viewChannelList responseDto=[{}]", responseDto);

		return responseDto;
	}

	
	@ApiOperation(value = "동영상 추가", notes = "동영상 추가")
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public ApiResponseDto<Object> addVideo(@RequestHeader(value = ValueConstants.AUTHORIZATION_TOKEN_NAME) String authorizationToken, 
			@RequestBody VideoAddRequestDto videoAddRequestDto){
		
		
		
		return null;
	}
}
