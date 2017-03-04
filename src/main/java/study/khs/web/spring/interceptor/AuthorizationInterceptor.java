package study.khs.web.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;
import study.khs.common.domain.AuthorizationUserInfo;
import study.khs.common.servie.AuthorizationUserInfoService;

@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthorizationUserInfoService authorizationUserInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		AuthorizationUserInfo userInfo = authorizationUserInfoService.getUserInfo(request);

		log.info("access=[{}], remoteAddress=[{}], userInfo=[{}], referer=[{}], userAgent=[{}]",
				new Object[] { //
						request.getRequestURI(), //
						request.getRemoteAddr(), //
						userInfo, //
						request.getHeader("referer"), //
						request.getHeader("User-Agent")//
				});

		if (userInfo.getId() == null) {
			// throw new InvalidAccessException();
		}

		return super.preHandle(request, response, handler);
	}
}
