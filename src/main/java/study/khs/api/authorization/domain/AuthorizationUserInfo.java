package study.khs.api.authorization.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AuthorizationUserInfo {

	private Long userId;

	private String userNickname;
}
