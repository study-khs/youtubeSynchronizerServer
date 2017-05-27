package study.khs.api.user.service;

import java.util.Map;

public interface SocialLoginService {

	Map<String, Object> getFacebookInformation(String facebookAccessToken);

	Map<String, Object> getNaverInformation(String naverAccessToken);
}
