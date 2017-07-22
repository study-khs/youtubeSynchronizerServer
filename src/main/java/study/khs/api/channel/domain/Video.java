package study.khs.api.channel.domain;

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
import study.khs.api.channel.dto.VideoAddRequestDto;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Video {

	public Video() {
		super();
	}

	public Video(Long channelId, VideoAddRequestDto videoAddRequestDto) {
		super();
		this.channelId = channelId;
		this.youtubeId = videoAddRequestDto.getYoutubeId();
		this.youtubeTitle = videoAddRequestDto.getYoutubeTitle();
		this.youtubeThumbnailsUrl = videoAddRequestDto.getYoutubeThumbnailsUrl();
		this.youtubeThumbnailsWidth = videoAddRequestDto.getYoutubeThumbnailsWidth();
		this.youtubeThumbnailsHeight = videoAddRequestDto.getYoutubeThumbnailsHeight();
		this.youtubeDuration = videoAddRequestDto.getYoutubeDuration();
	}

	@ApiModelProperty(value = "Video Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long videoId;

	@ApiModelProperty(value = "Channel Id")
	@Column(nullable = false)
	private Long channelId;

	@ApiModelProperty(value = "Youtube Id")
	@Column(nullable = false)
	private String youtubeId;

	@ApiModelProperty(value = "Youtube Title")
	@Column(nullable = false)
	private String youtubeTitle;

	@ApiModelProperty(value = "Youtube Thumbnails Url")
	@Column(nullable = false)
	private String youtubeThumbnailsUrl;

	@ApiModelProperty(value = "Youtube Thumbnails Width")
	@Column(nullable = false)
	private Long youtubeThumbnailsWidth;

	@ApiModelProperty(value = "Youtube Thumbnails Height")
	@Column(nullable = false)
	private Long youtubeThumbnailsHeight;

	@ApiModelProperty(value = "Youtube Duration")
	@Column(nullable = false)
	private String youtubeDuration;

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
