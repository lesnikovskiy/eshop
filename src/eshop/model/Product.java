package eshop.model;

import java.io.InputStream;
import java.math.BigDecimal;

public class Product {
	private int id;
	private String name;
	private BigDecimal price;
	private String mime;
	private InputStream file;
	private Category category;
	private boolean isDeleted;
	
	public Product() {}
	
	public Product(String name, BigDecimal price, Category category) {
		this.setName(name);
		this.setPrice(price);
		this.setCategory(category);
	}
	
	public Product(int id, String name, BigDecimal price, Category category) {
		this.setId(id);
		this.setName(name); 
		this.setPrice(price);
		this.setCategory(category);
	}
	
	public Product(String name, BigDecimal price, String mime, InputStream file, Category category) {
		this.setName(name); 
		this.setPrice(price);
		this.setMime(mime);
		this.setFile(file);
		this.setCategory(category);
	}
	
	public Product(int id, String name, BigDecimal price, String mime, InputStream file, Category category) {
		this.setId(id);
		this.setName(name); 
		this.setPrice(price);
		this.setMime(mime);
		this.setFile(file);
		this.setCategory(category);
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
