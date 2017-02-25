package study.khs.api.channel.domain;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Channel {

	@Id
	private String id;
}
