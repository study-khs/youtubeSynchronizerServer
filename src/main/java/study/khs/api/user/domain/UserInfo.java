package study.khs.api.user.domain;

import lombok.Data;
import study.khs.api.user.constants.UserType;

@Data
public class UserInfo {

	public UserInfo() {
		super();
	}

	public UserInfo(User user) {
		super();
		this.userId = user.getUserId();
		this.userType = user.getUserType();
		this.userLoginId = user.getUserLoginId();
		this.userNickname = user.getUserNickname();
	}

	private Long userId;
	private UserType userType;
	private String userLoginId;
	private String userNickname;
}
