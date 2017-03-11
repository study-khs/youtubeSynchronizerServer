package study.khs.api.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.khs.api.message.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
