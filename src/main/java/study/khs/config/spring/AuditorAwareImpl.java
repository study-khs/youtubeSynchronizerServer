package study.khs.config.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import study.khs.common.constants.ValueConstants;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {

		ServletRequestAttributes requestAttributes = //
				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String id = (String) request.getAttribute(ValueConstants.AUDITOR);
		if (id == null) {
			id = String.valueOf(System.currentTimeMillis());
		}

		return id;
	}
}
