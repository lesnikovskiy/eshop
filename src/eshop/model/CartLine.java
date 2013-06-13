package eshop.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CartLine {
	private Product product;
	private int quantity;
	
	public CartLine() {}
	
	public CartLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice() {
		BigDecimal itemCost = new BigDecimal(BigInteger.ZERO, 2);
		BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);
		
		Product currentProduct = this.getProduct();
		
		itemCost = currentProduct.getPrice().multiply(new BigDecimal(this.getQuantity()));
		
		return totalCost.add(itemCost);
	}	
}
