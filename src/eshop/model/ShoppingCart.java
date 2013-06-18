package eshop.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ShoppingCart {
	private List<CartLine> cartLines;
	
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO, 2);
		
		for (CartLine line : getCartLines()) {
			totalPrice = totalPrice.add(line.getPrice());
		}
		
		return totalPrice;
	}

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
}
