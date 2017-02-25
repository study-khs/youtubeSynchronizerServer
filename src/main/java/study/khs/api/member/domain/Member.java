package study.khs.api.member.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	private String id;
}
