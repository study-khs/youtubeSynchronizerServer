package study.khs.api.member.domain;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Member {

	@Id
	private String id;
}
