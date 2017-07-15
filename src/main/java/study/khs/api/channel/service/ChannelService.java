package study.khs.api.channel.service;

import study.khs.api.channel.domain.Channel;
import study.khs.api.channel.dto.ChannelCreateRequestDto;
import study.khs.common.dto.PageDto;

public interface ChannelService {

	Channel createChannel(ChannelCreateRequestDto channelCreateRequestDto);

	PageDto<Channel> viewChannelList(int page, int size);
}
