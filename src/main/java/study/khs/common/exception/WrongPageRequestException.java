package study.khs.common.exception;

import study.khs.common.constants.SystemConstants;

@SuppressWarnings("serial")
public class WrongPageRequestException extends BaseException {

	public WrongPageRequestException() {
		this(SystemConstants.WRONG_PAGE_REQUEST.getCode(), SystemConstants.WRONG_PAGE_REQUEST.getDesc());
	}

	public WrongPageRequestException(String message) {
		this(SystemConstants.WRONG_PAGE_REQUEST.getCode(), message);
	}

	public WrongPageRequestException(String code, String message) {
		super(code, message);
	}
}
