package study.khs.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessFilter implements Filter {

	private final Logger accessLog = LoggerFactory.getLogger("accessLog");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		long time = System.currentTimeMillis();

		ResponseWrapper responseWrapper = (ResponseWrapper) servletResponse;
		responseWrapper.setHeader("Connection", "close");
		responseWrapper.setHeader("Access-Control-Allow-Origin", "*");
		responseWrapper.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

		RequestWrapper requestWrapper = (RequestWrapper) servletRequest;

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
