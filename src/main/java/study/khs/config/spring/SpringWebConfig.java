package study.khs.config.spring;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import study.khs.support.http.RestTemplateLoggingRequestInterceptor;

@Configuration
public class SpringWebConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = getRestTemplateHasNotMessageConverter();

		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

		restTemplate.setMessageConverters(Arrays.asList(mappingJackson2HttpMessageConverter));

		return restTemplate;
	}

	private RestTemplate getRestTemplateHasNotMessageConverter() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(5000);
		simpleClientHttpRequestFactory.setReadTimeout(15000);

		RestTemplate restTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory));
		restTemplate.setInterceptors(Collections.singletonList(new RestTemplateLoggingRequestInterceptor()));
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
			}
		});

		return restTemplate;
	}
}
