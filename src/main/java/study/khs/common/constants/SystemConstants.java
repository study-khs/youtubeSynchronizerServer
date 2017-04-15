package study.khs.common.constants;

public enum SystemConstants {

	INVALID_ACCESS("user", "1000", "권한이 없거나 인증이 유효하지 않습니다."), //
	USER_LOGIN_EXCEPTION("user", "1100", "Id 또는 Password가 잘못되었습니다."), //
	MESSAGE_NOT_EXIST("message", "2000", "메시지가 존재하지 않습니다."),//
	;

	private String type;
	private String code;
	private String desc;

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	SystemConstants(String type, String code, String desc) {
		this.type = type;
		this.code = code;
		this.desc = desc;
	}
}
