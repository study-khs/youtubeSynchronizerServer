package study.khs.api.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import study.khs.api.user.constants.UserType;
import study.khs.api.user.dto.UserJoinRequestDto;

/**
 * User
 * 
 * @author JSPark
 *
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

	public User() {
		super();
	}

	public User(UserJoinRequestDto userJoinRequestDto) {
		super();
		this.userType = userJoinRequestDto.getUserType().getCode();
		this.userLoginId = userJoinRequestDto.getUserLoginId();
		this.userPassword = userJoinRequestDto.getUserPassword();
		this.userNickname = userJoinRequestDto.getUserNickname();
	}

	public User(UserType userType, String userLoginId, String userNickname) {
		super();
		this.userType = userType.getCode();
		this.userLoginId = userLoginId;
		this.userPassword = String.valueOf(userType.getCode());
		this.userNickname = userNickname;
	}

	@ApiModelProperty(value = "User Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@ApiModelProperty(value = "User Type")
	@Column(nullable = false)
	private Long userType;

	@ApiModelProperty(value = "User Login Id")
	@Column(nullable = false)
	private String userLoginId;

	@ApiModelProperty(value = "User Password")
	@Column(nullable = false)
	private String userPassword;

	@ApiModelProperty(value = "User Nickname")
	@Column(nullable = false)
	private String userNickname;

	@ApiModelProperty(hidden = true)
	@CreatedDate
	@Column(nullable = false)
	private Date createdAt;

	@ApiModelProperty(hidden = true)
	@CreatedBy
	@Column(nullable = false)
	private String createdBy;

	@ApiModelProperty(hidden = true)
	@LastModifiedDate
	@Column(nullable = false)
	private Date updatedAt;

	@ApiModelProperty(hidden = true)
	@LastModifiedBy
	@Column(nullable = false)
	private String updatedBy;

	public UserType getUserType() {
		return UserType.getEnum(this.userType);
	}

	public void setUserType(UserType userType) {
		this.userType = userType.getCode();
	}
}
