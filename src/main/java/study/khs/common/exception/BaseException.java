package study.khs.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {

	private String code;

	public BaseException() {
	}

	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}
}
