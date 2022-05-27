package personal.learning.entity;

import java.util.List;

import org.springframework.hateoas.Link;

public class Product {
	
	private int productId;
	
	private Brand brand;
	
	private String productName;
	
	private String category;
	
	private List<Link> listOflinks;

	public Product(int productId, Brand brand, String productName, String category) {
		this.productId = productId;
		this.brand = brand;
		this.productName = productName;
		this.category = category;
	}
	
	public Product() {
		productId = 0;
		this.brand = new Brand();
		this.productName = "###";
		category = "###";
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public List<Link> getListOflinks() {
		return listOflinks;
	}

	public void setListOflinks(List<Link> listOflinks) {
		this.listOflinks = listOflinks;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", brand=" + brand + ", productName=" + productName + ", category="
				+ category + ", listOflinks=" + listOflinks + "]";
	}
}
