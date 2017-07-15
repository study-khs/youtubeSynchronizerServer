package study.khs.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ApiResponseDto<T> {

	public static final String CODE_SUCCESS = "200";
	public static final String CODE_FAILURE = "500";

	public static final String MESSAGE_SUCCESS = "SUCCESS";
	public static final String MESSAGE_FAILURE = "FAILURE";

	@ApiModelProperty(value = "응답 코드(200:성공, 500:실패)")
	@Builder.Default
	private String resultCode = CODE_SUCCESS;

	@ApiModelProperty("응답 메시지")
	@Builder.Default
	private String resultMessage = MESSAGE_SUCCESS;

	@ApiModelProperty("응답 데이타")
	private T data;

	public static class ApiResponseDtoBuilder<T> {

		public ApiResponseDtoBuilder<T> success() {
			this.resultCode = CODE_SUCCESS;
			this.resultMessage = MESSAGE_SUCCESS;
			return this;
		}

		public ApiResponseDtoBuilder<T> failure() {
			this.resultCode = CODE_FAILURE;
			this.resultMessage = MESSAGE_FAILURE;
			return this;
		}
	}
}
