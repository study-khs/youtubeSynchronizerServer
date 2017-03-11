package study.khs.api.message.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.CommonException;

@SuppressWarnings("serial")
public class MessageNotExistException extends CommonException {

	public MessageNotExistException() {
		this(SystemConstants.MESSAGE_NOT_EXIST.getCode(), SystemConstants.MESSAGE_NOT_EXIST.getDesc());
	}

	public MessageNotExistException(String message) {
		this(SystemConstants.MESSAGE_NOT_EXIST.getCode(), message);
	}

	public MessageNotExistException(String code, String message) {
		super(code, message);
	}
}
