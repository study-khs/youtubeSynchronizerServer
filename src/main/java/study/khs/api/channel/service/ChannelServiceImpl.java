package study.khs.api.channel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.channel.domain.Channel;
import study.khs.api.channel.domain.Video;
import study.khs.api.channel.dto.ChannelCreateRequestDto;
import study.khs.api.channel.dto.VideoAddRequestDto;
import study.khs.api.channel.exception.ChannelAlreadyExistException;
import study.khs.api.channel.repository.ChannelRepository;
import study.khs.api.channel.repository.VideoRepository;
import study.khs.common.dto.PageDto;
import study.khs.common.exception.WrongPageRequestException;

@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private VideoRepository videoRepository;

	@Override
	public Channel createChannel(ChannelCreateRequestDto channelCreateRequestDto) {

		Channel channel = channelRepository.findOneByChannelManagerId(channelCreateRequestDto.getChannelManagerId());

		if (channel != null) {
			throw new ChannelAlreadyExistException();
		}

		Channel createdChannel = channelRepository.save(new Channel(channelCreateRequestDto));

		log.info("createChannel createdChannel=[{}]", createdChannel);

		return createdChannel;
	}

	@Override
	public Channel viewChannel(Long channelId) {

		Channel channel = channelRepository.findOne(channelId);

		return channel;
	}

	@Override
	public PageDto<Channel> viewChannelList(int page, int size) {

		Page<Channel> channelPage = channelRepository.findAll(new PageRequest(page - 1, size));

		if (!channelPage.hasContent()) {
			throw new WrongPageRequestException();
		}

		PageDto<Channel> pageDto = //
				PageDto.<Channel>builder()//
						.page(channelPage.getNumber() + 1)//
						.size(channelPage.getSize())//
						.hasNext(channelPage.hasNext())//
						.content(channelPage.getContent())//
						.build();

		return pageDto;
	}

	@Override
	public void deleteChannel(Long channelId) {

		channelRepository.delete(channelId);
	}

	@Override
	public Video createVideo(Long channelId, VideoAddRequestDto videoAddRequestDto) {

		Video video = videoRepository.save(new Video(channelId, videoAddRequestDto));

		return video;
	}

	@Override
	public Video viewVideo(Long videoId) {

		Video video = videoRepository.findOne(videoId);

		return video;
	}

	@Override
	public PageDto<Video> viewVideoList(Long channelId, int page, int size) {

		Page<Video> videoPage = videoRepository.findAllByChannelId(channelId, new PageRequest(page - 1, size));

		if (!videoPage.hasContent()) {
			throw new WrongPageRequestException();
		}

		PageDto<Video> pageDto = //
				PageDto.<Video>builder()//
						.page(videoPage.getNumber() + 1)//
						.size(videoPage.getSize())//
						.hasNext(videoPage.hasNext())//
						.content(videoPage.getContent())//
						.build();

		return pageDto;
	}

	@Override
	public void deleteVideo(Long videoId) {

		videoRepository.delete(videoId);
	}
}
