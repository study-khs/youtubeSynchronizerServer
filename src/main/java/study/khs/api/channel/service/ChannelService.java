package study.khs.api.channel.service;

import study.khs.api.channel.domain.Channel;
import study.khs.api.channel.domain.Video;
import study.khs.api.channel.dto.ChannelCreateRequestDto;
import study.khs.api.channel.dto.VideoAddRequestDto;
import study.khs.common.dto.PageDto;

public interface ChannelService {

	Channel createChannel(ChannelCreateRequestDto channelCreateRequestDto);

	Channel viewChannel(Long channelId);

	PageDto<Channel> viewChannelList(int page, int size);

	Video createVideo(Long channelId, VideoAddRequestDto videoAddRequestDto);

	PageDto<Video> viewVideoList(int page, int size);
}
