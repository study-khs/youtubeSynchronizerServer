package study.khs.common.servie;

import javax.servlet.http.HttpServletRequest;

import study.khs.common.domain.AuthorizationUserInfo;

public interface AuthorizationUserInfoService {

	public AuthorizationUserInfo getUserInfo(HttpServletRequest request);
}
