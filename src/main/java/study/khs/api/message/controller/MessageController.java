package study.khs.api.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.message.domain.Message;
import study.khs.api.message.repository.MessagePagingAndSortingRepository;
import study.khs.api.message.repository.MessageRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
    @Autowired
    private MessagePagingAndSortingRepository messagePagingAndSortingRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Message> getAllMessage() {

		log.info("getAllMessage");

		Page<Message> page = messagePagingAndSortingRepository.findAll(new PageRequest(0,20));
		List<Message> messages = page.getContent();

		log.info("getMessage message=[{}]", messages);

		return messages;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Message getMessage(@PathVariable String id) {

		log.info("getMessage id=[{}]", id);

		Message message = messageRepository.findOne(id);

		log.info("getMessage message=[{}]", message);

		return message;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Message postMessage(@RequestBody Message message) {

		log.info("postMessage message=[{}]", message);

		Message savedMessage = messageRepository.save(message);

		log.info("postMessage savedMessage=[{}]", savedMessage);

		return savedMessage;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Message putMessage(@RequestBody Message message) {

		log.info("putMessage message=[{}]", message);

		Message savedMessage = messageRepository.save(message);

		log.info("putMessage savedMessage=[{}]", savedMessage);

		return savedMessage;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMessage(@PathVariable String id) {

		log.info("deleteMessage id=[{}]", id);

		messageRepository.delete(id);
	}
}
