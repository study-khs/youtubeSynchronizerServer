package study.khs.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class CommonException extends RuntimeException {

	private String code;

	public CommonException() {
	}

	public CommonException(String code, String message) {
		super(message);
		this.code = code;
	}

	public CommonException(Throwable throwable) {
		super(throwable);
	}
}
