package personal.learning.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.learning.configuration.DBConfiguration;
import personal.learning.custom.exception.CustomException;
import personal.learning.model.entity.Brand;
import personal.learning.model.entity.Product;
import personal.learning.services.ProductService;

@Controller
@RequestMapping("/showroom")
public class HondaController {
	
	private static final Logger logger = LoggerFactory.getLogger(HondaController.class);
	
	private static final String NAME = "Honda";
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfiguration.class);
	ProductService productService = context.getBean("pService", ProductService.class);

	@RequestMapping(value = "/{brandName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity getProducts(@PathVariable("brandName") String brandName) {
		List<Product> productList = new ArrayList<Product>();
		try {
			if(brandName != null) {
				if(!brandName.equalsIgnoreCase(NAME)) {
					logger.error("Incorrect url!! Brand Name should be ["+NAME+"]");
					throw new CustomException("Invalid url");
				}
			}
			
			brandName = NAME;
			productList = productService.getProductsByBrandName(brandName);
			
			for(Product product: productList) {
				Brand brand = product.getBrand();
				Link selfLink = WebMvcLinkBuilder.linkTo(HondaController.class).slash(brand.getBrandName()).withSelfRel();
				List<Link> listOflinks = new ArrayList<Link>();
				listOflinks.add(selfLink);
				product.setListOflinks(listOflinks);
			}
		} catch(CustomException ex) {
			logger.error("Exception occured : "+ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}
}
