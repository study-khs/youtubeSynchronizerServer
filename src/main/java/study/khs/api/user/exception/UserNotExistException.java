package study.khs.api.user.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.BaseException;

@SuppressWarnings("serial")
public class UserNotExistException extends BaseException {

	public UserNotExistException() {
		this(SystemConstants.USER_NOT_EXIST.getCode(), SystemConstants.USER_NOT_EXIST.getDesc());
	}

	public UserNotExistException(String message) {
		this(SystemConstants.USER_NOT_EXIST.getCode(), message);
	}

	public UserNotExistException(String code, String message) {
		super(code, message);
	}
}
