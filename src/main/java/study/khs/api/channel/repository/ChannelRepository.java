package study.khs.api.channel.repository;

import org.springframework.data.repository.CrudRepository;

import study.khs.api.channel.domain.Channel;

/**
 * ChannelRepository
 * 
 * @author JSPark
 *
 */
public interface ChannelRepository extends CrudRepository<Channel, Long> {

}
