package study.khs.api.channel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.khs.api.channel.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
