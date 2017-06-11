package study.khs.api.user.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.BaseException;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends BaseException {

	public UserAlreadyExistException() {
		this(SystemConstants.USER_ALREADY_EXIST.getCode(), SystemConstants.USER_ALREADY_EXIST.getDesc());
	}

	public UserAlreadyExistException(String message) {
		this(SystemConstants.USER_ALREADY_EXIST.getCode(), message);
	}

	public UserAlreadyExistException(String code, String message) {
		super(code, message);
	}
}
