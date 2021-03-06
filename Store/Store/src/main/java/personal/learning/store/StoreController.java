package personal.learning.store;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import personal.learning.entity.Product;

@Controller
public class StoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	private String mahindra = "Mahindra";
	
	private String honda = "Honda";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("Loading home page");
		return "home";
	}
	
	@RequestMapping(value = "/showroom/{brandName}")
	public ModelAndView brand(@PathVariable("brandName") String brandName) {
		ModelAndView modelAndView = new ModelAndView("productInfo");
		ResponseEntity<List<Product>> responseEntity = null;
		List<Product> productList = new ArrayList<Product>();
		switch(brandName.toLowerCase()) {
			case "mahindra":
				responseEntity = restTemplate.exchange("http://mahindra-app/showroom/mahindra", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
				productList = responseEntity.getBody();
				modelAndView.addObject("productList", productList);
				modelAndView.addObject("brandName", mahindra);
			break;
			case "honda":
				responseEntity = restTemplate.exchange("http://honda-app/showroom/honda", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
				productList = responseEntity.getBody();
				modelAndView.addObject("productList", productList);
				modelAndView.addObject("brandName", honda);
			break;
			default:
				modelAndView = new ModelAndView("redirect: /error");
		}
		return modelAndView;
	}
}
