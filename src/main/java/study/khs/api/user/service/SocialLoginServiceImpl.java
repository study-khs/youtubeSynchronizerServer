package study.khs.api.user.service;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SocialLoginServiceImpl implements SocialLoginService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Map<String, Object> getFacebookInformation(String facebookAccessToken) {

		log.info("getFacebookInformation facebookAccessToken=[{}]", facebookAccessToken);

		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		URI uri = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/me").queryParam("fileds", "id,name")
				.queryParam("access_token", facebookAccessToken).build().encode().toUri();

		Map<String, Object> facebookInformation = restTemplate
				.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Map<String, Object>>() {
				}).getBody();

		log.info("getFacebookInformation facebookInformation=[{}]", facebookInformation);

		return facebookInformation;
	}

	@Override
	public Map<String, Object> getNaverInformation(String naverAccessToken) {

		log.info("getNaverInformation naverAccessToken=[{}]", naverAccessToken);

		return null;
	}
}
