package study.khs.api.channel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.channel.domain.Channel;
import study.khs.api.channel.dto.ChannelCreateRequestDto;
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

		Channel createdChannel = channelRepository.save(new Channel(channelCreateRequestDto));

		log.info("createChannel createdChannel=[{}]", createdChannel);

		return createdChannel;
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
}
