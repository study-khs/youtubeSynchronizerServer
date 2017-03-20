package study.khs.api.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.message.domain.Message;
import study.khs.api.message.exception.MessageNotExistException;
import study.khs.api.message.repository.MessageRepository;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message selectMessage(Long id) {

		log.info("selectMessage id=[{}]", id);

		Message message = messageRepository.findOne(id);

		log.info("selectMessage message=[{}]", message);

		return message;
	}

	@Override
	public Page<Message> selectMessagePage(Integer page, Integer size) {

		log.info("selectMessageList page=[{}], size=[{}]", page, size);

		PageRequest pageRequest = new PageRequest(page - 1, size);
		Page<Message> messagePage = messageRepository.findAll(pageRequest);

		log.info("selectMessageList messagePage=[{}]", messagePage);

		return messagePage;
	}

	@Override
	public Message createMessage(String text) {

		log.info("createMessage text=[{}]", text);

		Message message = messageRepository.save(new Message(text));

		log.info("createMessage message=[{}]", message);

		return message;
	}

	@Override
	public Message updateMessage(Message message) {

		log.info("updateMessage message=[{}]", message);

		if (messageRepository.exists(message.getId())) {

			Message savedMessage = messageRepository.save(message);

			log.info("updateMessage update savedMessage=[{}]", savedMessage);

			return savedMessage;

		} else {
			log.info("updateMessage message not exist id=[{}]", message.getId());
			throw new MessageNotExistException();
		}
	}

	@Override
	public void deleteMessage(Long id) {

		log.info("deleteMessage id=[{}]", id);

		if (messageRepository.exists(id)) {

			messageRepository.delete(id);

			log.info("deleteMessage delete success id=[{}]", id);

			return;
		} else {
			log.info("deleteMessage message not exist id=[{}]", id);
			throw new MessageNotExistException();
		}
	}
}
