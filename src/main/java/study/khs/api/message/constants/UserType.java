package study.khs.api.message.constants;

public enum UserType {

	STANDARD(100), //
	FACEBOOK(200), //
	NAVER(300), //
	KAKAO(400),//
	;

	private Long code;

	private UserType(Integer code) {
		this.code = code.longValue();
	}

	public Long getCode() {
		return code;
	}

	public static UserType getType(Long id) {

		UserType userType = null;
		for (UserType item : UserType.values()) {
			if (item.getCode() == id) {
				userType = item;
				break;
			}
		}
		return userType;
	}
}
