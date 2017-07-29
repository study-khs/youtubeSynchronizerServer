package study.khs.api.channel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.khs.api.channel.domain.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

	Channel findOneByChannelManagerId(Long channelManagerId);
}
