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

	void deleteChannel(Long channelId);

	Video createVideo(Long channelId, VideoAddRequestDto videoAddRequestDto);

	Video viewVideo(Long videoId);

	PageDto<Video> viewVideoList(Long channelId, int page, int size);

	void deleteVideo(Long videoId);
}
