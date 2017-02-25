package study.khs.api.channel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Channel {

	@Id
	private String id;
}
