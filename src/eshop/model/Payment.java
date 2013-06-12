package eshop.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Payment {
	private static BigDecimal itemCost = new BigDecimal(BigInteger.ZERO, 2);
	private static BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);
	
	public static BigDecimal calculateCost(int quantity, BigDecimal price) {
		itemCost = price.multiply(new BigDecimal(quantity));
		
		return totalCost.add(itemCost);
	}
}
