<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, eshop.model.*, java.math.*" %>
<%! String cartLinesKey = "products_session_key"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Lines</title>
<style>
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	.header {float: left; display: block; padding:0; margin: 0;}
	.aside {float:right; margin:0; padding:0;}
	.aside a.checkout { font:12px Arial; color:#333; text-decoration: underline; }
	.entry { border-bottom:1px solid #333; padding:0; margin:5px 0; } 
	.page .descr {float:left; width:300px;}
	.page .qty { float: right; margin:0; padding: 0; }
	.page .qty p { background:#EEE; border-radius: 5px; padding: 4px; font:bold 12px Arial; }
	.page .buttons .cart-button { 
		color:#333; background:#EEE; border:1px solid #333; 
		cursor: pointer; border-radius:3px; 
		font:bold 12px Arial;
		display: block; float: right;
		width:150px;
	}
	.page .buttons input[type=submit].cart-button:nth-child(odd) {margin-right:3px;}
	.clear { clear:both; }
</style>
</head>
<body>
	<div class="page">
		<div class="header">
			<h1>Selected items:</h1>
		</div>		
		<div class="clear"></div>
		<%
			List<CartLine> cartLines = (ArrayList<CartLine>) session.getAttribute(cartLinesKey);
			if (cartLines != null) {
				%>
				<div class="entry">
					<div class="descr"><p><strong>Name</strong></p></div>
					<div class="descr"><p><strong>Price</strong></p></div>
					<div class="qty"><p><strong>Quantity</strong></p></div>
					<div class="clear"></div>
				</div>
				<%		
				
				BigDecimal total = new BigDecimal(BigInteger.ZERO, 2);
				for (CartLine c : cartLines) {
					BigDecimal cost = c.getQuantity() > 1 
									? Payment.calculateCost(c.getQuantity(), c.getProduct().getPrice()) 
									: c.getProduct().getPrice();
									
					total = total.add(cost);
				%>
					<div class="entry">
						<div class="descr">
							<p><%= c.getProduct().getName() %></p>
						</div>
						<div class="descr">
							<p>$<%= cost %></p>
						</div>
						<div class="qty">
							<p><%= c.getQuantity() %></p>
						</div>
						<div class="clear"></div>
					</div>					
				<%
				}				
				%>
					<div class="entry">
					<div class="descr"><p><strong>Total:</strong></p></div>
					<div class="descr"><p><strong>$<%= total %></strong></p></div>
					<div class="qty"><p></p></div>
					<div class="clear"></div>
				</div>
				<%
			}
		%>
		<div class="buttons">
			<form action="index.jsp">
				<input type="submit" value="continue shopping" class="cart-button" />
			</form>
			<form action="checkout.jsp">
				<input type="submit" value="checkout" class="cart-button" />
			</form>
		</div>
	</div>
</body>
</html>