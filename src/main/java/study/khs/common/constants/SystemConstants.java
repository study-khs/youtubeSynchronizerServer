package study.khs.common.constants;

public enum SystemConstants {

	ERROR("System", "1000", "서버에서 에러가 발생했습니다."), //
	WRONG_FORM("System", "1100", "잘못된 형식입니다."), //
	WRONG_PAGE_REQUEST("System", "1200", "잘못된 페이지 요청입니다."), //

	INVALID_ACCESS("Authorization", "2000", "권한이 없거나 인증이 유효하지 않습니다."), //

	USER_NOT_EXIST("User", "3000", "계정이 존재하지 않습니다."), //
	WRONG_PASSWORD("User", "3100", "잘못된 비밀번호입니다."), //
	USER_ALREADY_EXIST("User", "3200", "계정이 이미 존재합니다."), //

	CHANNEL_ALREADY_EXIST("Channel", "3200", "채널이 이미 존재합니다."), //

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
