package study.khs.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import study.khs.api.message.constants.UserType;

/**
 * UserJoinRequestDto
 * 
 * @author JSPark
 *
 */
@Data
public class UserJoinRequestDto {

	@ApiModelProperty(required = true)
	private UserType userType;

	@ApiModelProperty(required = true)
	private String userLoginId;

	@ApiModelProperty(required = true)
	private String userPassword;

	@ApiModelProperty(required = true)
	private String userNickname;
}
