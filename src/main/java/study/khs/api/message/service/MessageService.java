package study.khs.api.message.service;

import org.springframework.data.domain.Page;

import study.khs.api.message.domain.Message;

public interface MessageService {

	public Message selectMessage(Long id);

	public Page<Message> selectMessagePage(Integer page, Integer size);

	public Message createMessage(String text);

	public Message updateMessage(Message message);

	public void deleteMessage(Long id);
}
