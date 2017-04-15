package study.khs.api.authorization.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.BaseException;

@SuppressWarnings("serial")
public class UserJoinException extends BaseException {

	public UserJoinException() {
		this(SystemConstants.USER_JOIN_EXCEPTION.getCode(), SystemConstants.USER_JOIN_EXCEPTION.getDesc());
	}

	public UserJoinException(String message) {
		this(SystemConstants.USER_JOIN_EXCEPTION.getCode(), message);
	}

	public UserJoinException(String code, String message) {
		super(code, message);
	}
}
