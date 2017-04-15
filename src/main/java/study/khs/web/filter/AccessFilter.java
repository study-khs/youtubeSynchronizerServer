package study.khs.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccessFilter implements Filter {

	private final Logger accessLog = LoggerFactory.getLogger("accessLog");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		long time = System.currentTimeMillis();

		ResponseWrapper responseWrapper = null;
		if (servletRequest instanceof ResponseWrapper) {
			responseWrapper = (ResponseWrapper) servletResponse;
		} else {
			responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
		}

		responseWrapper.setHeader("Connection", "close");
		responseWrapper.setHeader("Access-Control-Allow-Origin", "*");
		responseWrapper.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

		RequestWrapper requestWrapper = null;
		if (servletRequest instanceof RequestWrapper) {
			requestWrapper = (RequestWrapper) servletRequest;
		} else {
			requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
		}

		filterChain.doFilter(requestWrapper, responseWrapper);

		Object object = requestWrapper.getAttribute("invoker.handler");
		if (object == null) {

			accessLog.info("{}\t{}\t{}\t{}\t{}\t{}\t{}\t",
					new Object[] { //
							requestWrapper.isSecure() ? "HTTPS" : "HTTP", //
							requestWrapper.getMethod(), //
							(System.currentTimeMillis() - time), //
							requestWrapper.getRequestURI(), //
							requestWrapper.getQueryString(), //
							requestWrapper.getRemoteAddr(), //
							requestWrapper.getHeader("referer"), //
							requestWrapper.getHeader("User-Agent")//
					});

			requestWrapper.setAttribute("invoker.handler", "Y");
		}
	}

	@Override
	public void destroy() {
	}
}
