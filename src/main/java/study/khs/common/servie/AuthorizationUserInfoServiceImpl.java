package study.khs.common.servie;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.user.domain.User;
import study.khs.api.user.repository.UserRepository;
import study.khs.common.constants.ValueConstants;
import study.khs.common.domain.AuthorizationUserInfo;

@Slf4j
@Service
public class AuthorizationUserInfoServiceImpl implements AuthorizationUserInfoService {

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unchecked")
	public AuthorizationUserInfo getUserInfo(HttpServletRequest request) {

		String authorizationToken = request.getHeader(ValueConstants.AUTHORIZATION_TOKEN_NAME);

		if(authorizationToken != null){
			authorizationToken = authorizationToken.replaceAll("'", "\"");
			authorizationToken = authorizationToken.replaceAll("\\\\", "");
		}
		
		AuthorizationUserInfo userInfo = new AuthorizationUserInfo();
		if (authorizationToken != null) {
			try {
				Map<String, Object> authorizationMap;
				authorizationMap = objectMapper.readValue(authorizationToken, HashMap.class);
				BeanUtils.populate(userInfo, authorizationMap);
			} catch (Exception exception) {
				log.error("Invalid Token authorizationToken=[{}], Exception=[{}]", authorizationToken, exception);
			}
		}
		
		if(userInfo.getId() != null){
			User user = userRepository.findOne(userInfo.getId());
			if(user != null){
				// userInfo 세팅
			}
		}

		request.setAttribute(ValueConstants.AUTHORIZATION_USER_INFO, userInfo);

		return userInfo;
	}
}
