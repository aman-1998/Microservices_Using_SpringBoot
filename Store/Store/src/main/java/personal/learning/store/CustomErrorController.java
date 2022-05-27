package personal.learning.store;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {
	
	public String getErrorPath() {
		return "/error";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	}
}
