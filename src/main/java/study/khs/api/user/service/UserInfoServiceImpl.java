package study.khs.api.user.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.khs.api.user.domain.UserInfo;
import study.khs.common.constants.ValueConstants;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private TextEncryptor textEncryptor;

	@Autowired
	private ObjectMapper objectMapper;

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
				Map<String, Object> map = objectMapper.readValue(authorizationToken, HashMap.class);
			} catch (IOException e) {

			}
		}

		return null;
	}
}
