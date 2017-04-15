package study.khs.api.authorization.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import study.khs.api.user.constants.UserType;

/**
 * UserJoinRequestDto
 * 
 * @author JSPark
 *
 */
@Data
public class UserLoginWithFacebookRequestDto {

	@ApiModelProperty(required = true)
	private UserType userType;

	@ApiModelProperty(required = true)
	private String token;
}
