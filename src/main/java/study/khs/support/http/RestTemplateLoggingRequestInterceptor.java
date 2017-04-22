package study.khs.support.http;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateLoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		traceRequest(request, body);

		long startTimeMillis = System.currentTimeMillis();
		ClientHttpResponse response = execution.execute(request, body);
		long endTimeMillis = System.currentTimeMillis();

		traceResponse(response, request.getURI());

		log.info("execute time=[{}] uri=[{}]", endTimeMillis - startTimeMillis, request.getURI());

		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n [Request] ").append(request.getMethod()).append(" ").append(request.getURI());
		stringBuilder.append("\n [Body]\n").append(new String(body, StandardCharsets.UTF_8));
		log.info(stringBuilder.toString());
	}

	private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		byte[] bodyData = FileCopyUtils.copyToByteArray(response.getBody());
		stringBuilder.append("\n [Response] ").append(uri);
		stringBuilder.append("\n [Status] ").append(response.getStatusCode().value());
		stringBuilder.append("\n [Body]\n").append(new String(bodyData, StandardCharsets.UTF_8));
		log.info(stringBuilder.toString());
	}
}
