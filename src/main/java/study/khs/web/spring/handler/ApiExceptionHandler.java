package study.khs.web.spring.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import study.khs.common.dto.ApiResponseDto;
import study.khs.common.exception.BaseException;

@Slf4j
@ControllerAdvice(basePackages = { "study.khs.api" })
public class ApiExceptionHandler {

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public ResponseEntity<Object> handleBaseException(HttpServletRequest request, BaseException baseException) {

		log.error("ApiExceptionHandler handleBaseException baseException=[{}]", baseException);

		ApiResponseDto<Object> responseDto = //
				ApiResponseDto.<Object>builder()//
						.resultCode(baseException.getCode())//
						.resultMessage(baseException.getMessage())//
						.data(null)//
						.build();

		return ResponseEntity.ok().body(responseDto);
	}
}
