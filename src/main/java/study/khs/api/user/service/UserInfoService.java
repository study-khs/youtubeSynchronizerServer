package study.khs.api.user.service;

import javax.servlet.http.HttpServletRequest;

import study.khs.api.user.domain.UserInfo;

public interface UserInfoService {

	UserInfo getUserInfo(HttpServletRequest request);
}
