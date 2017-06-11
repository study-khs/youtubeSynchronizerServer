package study.khs.api.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.user.domain.User;
import study.khs.api.user.domain.UserInfo;
import study.khs.api.user.repository.UserRepository;
import study.khs.common.constants.ValueConstants;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private TextEncryptor textEncryptor;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getUserInfo(HttpServletRequest request) {
		String authorizationToken = request.getHeader(ValueConstants.AUTHORIZATION_TOKEN_NAME);

		if (authorizationToken != null) {
			authorizationToken = authorizationToken.replaceAll("'", "\"");
			authorizationToken = authorizationToken.replaceAll("\\\\", "");
		}

		UserInfo userInfo = new UserInfo();
		if (authorizationToken != null) {
			authorizationToken = textEncryptor.decrypt(authorizationToken);
			try {
				Map<String, Object> userInfoMap = objectMapper.readValue(authorizationToken, HashMap.class);
				BeanUtils.populate(userInfo, userInfoMap);
			} catch (Exception exception) {
				log.error("Invalid Token authorizationToken=[{}], Exception=[{}]", authorizationToken, exception);
			}
		}

		if (userInfo.getUserId() != null) {
			User user = userRepository.findOne(userInfo.getUserId());
			if (user != null) {
				// 추가로 필요한 userInfo 세팅, 우리는 필요없을 듯
			}
		}

		return userInfo;
	}
}
