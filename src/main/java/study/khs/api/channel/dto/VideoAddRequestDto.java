package study.khs.api.channel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoAddRequestDto {

	@ApiModelProperty(required = true)
	private String channelId;

	@ApiModelProperty(required = true)
	private String youtubeId;

	@ApiModelProperty(required = true)
	private String youtubeTitle;

	@ApiModelProperty(required = true)
	private String youtubeThumbnailsUrl;

	@ApiModelProperty(required = true)
	private Long youtubeThumbnailsWidth;

	@ApiModelProperty(required = true)
	private Long youtubeThumbnailsHeight;

	@ApiModelProperty(required = true)
	private String youtubeDuration;
}
