package study.khs.api.channel.dto;

import java.util.List;

import lombok.Data;
import study.khs.api.user.dto.UserInfoDto;

@Data
public class ChannelInfoDto {

	private Long channelId;

	private UserInfoDto channelManager;

	private List<String> youtubeUrlList;

	private List<UserInfoDto> memberList;

	private boolean activated;
}
