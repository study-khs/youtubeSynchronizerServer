package study.khs.api.message.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Message {

	public Message() {
		super();
	}

	public Message(String text) {
		super();
		this.text = text;
	}

	public Message(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String text;
}
