package study.khs.api.channel.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Channel
 * 
 * @author JSPark
 *
 */
@Data
@Entity
public class Channel {

	@ApiModelProperty(value = "Channel Id")
	@Id
	private Long channelId;

	/**  */
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
}
