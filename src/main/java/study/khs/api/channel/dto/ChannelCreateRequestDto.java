package study.khs.api.channel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelCreateRequestDto {

	@ApiModelProperty(required = true)
	private String channelTitle;

	@ApiModelProperty(hidden = true)
	private Long channelManagerId;
}
