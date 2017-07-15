package study.khs.common.exception;

import study.khs.common.constants.SystemConstants;

@SuppressWarnings("serial")
public class InvalidFormException extends BaseException {

	public InvalidFormException() {
		this(SystemConstants.WRONG_FORM.getCode(), SystemConstants.WRONG_FORM.getDesc());
	}

	public InvalidFormException(String message) {
		this(SystemConstants.WRONG_FORM.getCode(), message);
	}

	public InvalidFormException(String code, String message) {
		super(code, message);
	}
}
