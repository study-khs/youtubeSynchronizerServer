package study.khs.api.user.domain;

import lombok.Data;

@Data
public class UserInfo {

	public UserInfo() {
		super();
	}

	public UserInfo(User user) {
		super();
		this.userId = user.getUserId();
		this.userType = user.getUserType().toString();
		this.userLoginId = user.getUserLoginId();
		this.userNickname = user.getUserNickname();
	}

	private Long userId;
	private String userType;
	private String userLoginId;
	private String userNickname;
}
