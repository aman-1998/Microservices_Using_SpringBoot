package personal.learning.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.model.dao.ProductDAO;
import personal.learning.model.entity.Product;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDAO productRepo;

	public List<Product> getProductsByBrandName(String brandName) {
		List<Product> productList =  productRepo.getProductsByBrandName(brandName);
		if(productList.isEmpty()) {
			logger.warn("There is no product available for brand_name["+brandName+"]");
		}
		return productList;
	}

}
