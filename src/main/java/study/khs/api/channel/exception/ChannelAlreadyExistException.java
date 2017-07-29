package study.khs.api.channel.exception;

import study.khs.common.constants.SystemConstants;
import study.khs.common.exception.BaseException;

@SuppressWarnings("serial")
public class ChannelAlreadyExistException extends BaseException {

	public ChannelAlreadyExistException() {
		this(SystemConstants.CHANNEL_ALREADY_EXIST.getCode(), SystemConstants.CHANNEL_ALREADY_EXIST.getDesc());
	}

	public ChannelAlreadyExistException(String message) {
		this(SystemConstants.CHANNEL_ALREADY_EXIST.getCode(), message);
	}

	public ChannelAlreadyExistException(String code, String message) {
		super(code, message);
	}
}
