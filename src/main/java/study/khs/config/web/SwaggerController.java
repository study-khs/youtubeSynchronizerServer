package study.khs.config.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

	@RequestMapping("/")
	public String swagger() {
		return "redirect:/swagger-ui.html";
	}
}
