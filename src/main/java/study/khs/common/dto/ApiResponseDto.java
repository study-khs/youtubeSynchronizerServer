package study.khs.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto<T> {

	public static final String CODE_SUCCESS = "200";
	public static final String CODE_FAILURE = "500";

	@ApiModelProperty(value = "응답 코드(200:성공, 500:실패)")
	private String resultCode = CODE_SUCCESS;

	@ApiModelProperty("응답 메시지")
	private String resultMessage = "성공";

	@ApiModelProperty("응답 데이타")
	private T data;

	public static class ApiResponseDtoBuilder<T> {
		private String resultCode = CODE_SUCCESS;
		private String resultMessage = "성공";
	}
}
