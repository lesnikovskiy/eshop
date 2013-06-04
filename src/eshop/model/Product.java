package eshop.model;

import java.math.BigDecimal;

public class Product {
	private int id;
	private String name;
	private BigDecimal price;
	
	public Product(int id, String name, BigDecimal price) {
		this.setId(id);
		this.setName(name); 
		this.setPrice(price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
