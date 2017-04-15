package study.khs.api.user.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.BaseException;

@SuppressWarnings("serial")
public class UserLoginException extends BaseException {

	public UserLoginException() {
		this(SystemConstants.USER_LOGIN_EXCEPTION.getCode(), SystemConstants.USER_LOGIN_EXCEPTION.getDesc());
	}

	public UserLoginException(String message) {
		this(SystemConstants.USER_LOGIN_EXCEPTION.getCode(), message);
	}

	public UserLoginException(String code, String message) {
		super(code, message);
	}
}
