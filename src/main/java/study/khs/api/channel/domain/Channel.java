package study.khs.api.channel.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import study.khs.api.channel.dto.ChannelCreateRequestDto;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Channel {

	public Channel() {
		super();
	}

	public Channel(ChannelCreateRequestDto channelCreateRequestDto) {
		super();
		this.channelManagerId = channelCreateRequestDto.getChannelManagerId();
		this.channelTitle = channelCreateRequestDto.getChannelTitle();
	}

	@ApiModelProperty(value = "Channel Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long channelId;

	@ApiModelProperty(value = "Channel Manager Id")
	@Column(nullable = false)
	private Long channelManagerId;

	@ApiModelProperty(value = "Channel Title")
	@Column(nullable = false)
	private String channelTitle;

	@ApiModelProperty(value = "Current Video")
	@OneToOne
	@JoinColumn(name = "currentVideoId")
	private Video currentVideo;

	@ApiModelProperty(hidden = true)
	@CreatedDate
	@Column(nullable = false)
	private Date createdAt;

	@ApiModelProperty(hidden = true)
	@CreatedBy
	@Column(nullable = false)
	private Long createdBy;

	@ApiModelProperty(hidden = true)
	@LastModifiedDate
	@Column(nullable = false)
	private Date updatedAt;

	@ApiModelProperty(hidden = true)
	@LastModifiedBy
	@Column(nullable = false)
	private Long updatedBy;
}
