package study.khs.common.exception;

import study.khs.common.constants.SystemConstants;

@SuppressWarnings("serial")
public class InvalidFormException extends BaseException {

	public InvalidFormException() {
		this(SystemConstants.INVALID_FORM.getCode(), SystemConstants.INVALID_FORM.getDesc());
	}

	public InvalidFormException(String message) {
		this(SystemConstants.INVALID_FORM.getCode(), message);
	}

	public InvalidFormException(String code, String message) {
		super(code, message);
	}
}
