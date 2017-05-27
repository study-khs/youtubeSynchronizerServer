package study.khs.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import study.khs.common.constants.SystemConstants;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {

	private String code;

	public BaseException() {
		this(SystemConstants.ERROR.getCode(), SystemConstants.ERROR.getDesc());
	}

	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}
}
