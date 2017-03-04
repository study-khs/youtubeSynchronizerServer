package study.khs.common.exception;

import study.khs.common.constants.SystemConstants;

@SuppressWarnings("serial")
public class InvalidAccessException extends CommonException {

	public InvalidAccessException() {
		this(SystemConstants.INVALID_ACCESS.getCode(), SystemConstants.INVALID_ACCESS.getDesc());
	}

	public InvalidAccessException(String message) {
		this(SystemConstants.INVALID_ACCESS.getCode(), message);
	}

	public InvalidAccessException(String code, String message) {
		super(code, message);
	}

}
