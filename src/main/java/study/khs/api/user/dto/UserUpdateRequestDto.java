package study.khs.api.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserUpdateRequestDto {

	@ApiModelProperty(required = true)
	private Long userId;

	@ApiModelProperty(required = true)
	private String userNickname;
}
