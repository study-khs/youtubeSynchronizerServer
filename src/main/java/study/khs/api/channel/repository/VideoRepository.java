package study.khs.api.channel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import study.khs.api.channel.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	Page<Video> findAllByChannelId(Long channelId, Pageable pageable);
}
