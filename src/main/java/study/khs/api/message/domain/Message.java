package study.khs.api.message.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Message {

	@Id
	private String id;

	@Column(nullable = false)
	private String message;
}
