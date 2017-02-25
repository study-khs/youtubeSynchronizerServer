package study.khs.api.message.repository;

import org.springframework.data.repository.CrudRepository;

import study.khs.api.message.domain.Message;

public interface MessageRepository extends CrudRepository<Message, String>{

}
