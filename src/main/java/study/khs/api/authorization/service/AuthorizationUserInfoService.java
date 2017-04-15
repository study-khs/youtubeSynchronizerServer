package study.khs.api.authorization.service;

import javax.servlet.http.HttpServletRequest;

import study.khs.api.authorization.domain.AuthorizationUserInfo;

public interface AuthorizationUserInfoService {

	public AuthorizationUserInfo getUserInfo(HttpServletRequest request);
}
