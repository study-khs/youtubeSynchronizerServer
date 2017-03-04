package study.khs.common.constants;

public enum SystemConstants {

	INVALID_ACCESS("system", "1000", "권한이 없거나 인증이 유효하지 않습니다.");

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
