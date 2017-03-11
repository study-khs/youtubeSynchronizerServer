package study.khs.api.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.message.domain.Message;
import study.khs.api.message.service.MessageService;
import study.khs.common.dto.PageDto;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Message getMessage(@PathVariable Long id) {

		log.info("getMessage id=[{}]", id);

		Message message = messageService.selectMessage(id);

		log.info("getMessage message=[{}]", message);

		return message;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody PageDto<Message> getMessageList(//
			@RequestParam(defaultValue = "1") Integer page, //
			@RequestParam(defaultValue = "10") Integer size) {

		log.info("getMessageList page=[{}] size=[{}]", page, size);

		Page<Message> messagePage = messageService.selectMessagePage(page, size);

		PageDto<Message> messagePageDto = PageDto.<Message>builder()//
				.page(page)//
				.size(size)//
				.hasNext(messagePage.hasNext())//
				.content(messagePage.getContent())//
				.build();

		log.info("getMessageList messagePageDto=[{}]", messagePageDto);

		return messagePageDto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Message createMessage(@RequestBody String text) {

		log.info("createMessage text=[{}]", text);

		Message message = messageService.createMessage(text);

		log.info("createMessage message=[{}]", message);

		return message;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Message updateMessage(@RequestBody Message message) {

		log.info("updateMessage message=[{}]", message);

		Message updatedMessage = messageService.updateMessage(message);

		log.info("updateMessage updatedMessage=[{}]", updatedMessage);

		return updatedMessage;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMessage(@PathVariable Long id) {

		log.info("deleteMessage id=[{}]", id);

		messageService.deleteMessage(id);

		return;
	}
}
