package eshop.model;

import java.io.InputStream;
import java.math.BigDecimal;

public class Product {
	private int id;
	private String name;
	private BigDecimal price;
	private String mime;
	private InputStream file;
	
	public Product() {}
	
	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	public Product(int id, String name, BigDecimal price) {
		this.setId(id);
		this.setName(name); 
		this.setPrice(price);
	}
	
	public Product(String name, BigDecimal price, String mime, InputStream file) {
		this.setName(name); 
		this.setPrice(price);
		this.mime = mime;
		this.file = file;
	}
	
	public Product(int id, String name, BigDecimal price, String mime, InputStream file) {
		this.setId(id);
		this.setName(name); 
		this.setPrice(price);
		this.mime = mime;
		this.file = file;
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

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
}
