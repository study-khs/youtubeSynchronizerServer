package study.khs.api.user.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.BaseException;

@SuppressWarnings("serial")
public class WrongPasswordException extends BaseException {

	public WrongPasswordException() {
		this(SystemConstants.WRONG_PASSWORD.getCode(), SystemConstants.WRONG_PASSWORD.getDesc());
	}

	public WrongPasswordException(String message) {
		this(SystemConstants.WRONG_PASSWORD.getCode(), message);
	}

	public WrongPasswordException(String code, String message) {
		super(code, message);
	}
}
