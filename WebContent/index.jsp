<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>   
<%@ page session="true" import="java.util.List, java.util.ArrayList, eshop.model.Product" %>
<%! String cartLinesKey = "products_session_key"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<style>
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	.header {float: left; display: block; padding:0; margin: 0;}
	.aside {float:right; margin:0; padding:0;}
	.aside a.checkout { font:12px Arial; color:#333; text-decoration: underline; }
	.entry { border-bottom:1px solid #333; padding:0; margin:5px 0; } 
	.page .descr {float:left; width:300px;}
	.page .buttons { float: right; margin:0; padding: 0; }
	.page .buttons .cart-button { 
		color:#333; background:#EEE; border:1px solid #333; 
		cursor: pointer; border-radius:3px; 
		font:bold 12px Arial;
		display: block; float: right;
	}
	.clear { clear:both; }
</style>
</head>
<body>
	<div class="page">
	<div class="header">
		<h3>Products</h3>
	</div>
	<div class="aside">
		<p>
			<a href="cart.jsp" class="checkout">Shopping cart:
					(<%
					List<Product> cartLines = (ArrayList<Product>) session.getAttribute(cartLinesKey);
					out.print(cartLines == null ? 0 : cartLines.size());
					%>) items
			</a>
		</p>
	</div>
	<div class="clear"></div>
	<% 
		List<Product> products = (ArrayList<Product>) request.getAttribute("products");
		if (products == null) {
			response.sendRedirect("/eshop/products");
		} else if (products.size() <= 0) {
			%>
			<p>Products catalog is empty.</p>
			<%
		} else {
			for (Product p : products) {
				%>
					<div class="entry">
						<div class="descr">
							<p><%= p.getName() %></p>
							<p>Price: $<%= p.getPrice() %></p>	
							<p><a href="/eshop/products?id=<%= p.getId() %>">[edit]</a></p>
						</div>
						<div class="buttons">
							<p class="add-to-cart">
								<form method="post" action="/eshop/products">
									<input type="hidden" name="action" value="addToCart" />
									<input type="hidden" name="id4cart" value="<%= p.getId() %>" />
									<input type="submit" value="add to cart" class="cart-button" />
								</form>
							</p>
						</div>	
						<div class="clear"></div>										
					</div>
				<%
			}
		}
	%>
	<div>
		<a href="edit.jsp">Add new product</a>
	</div>
	</div>
</body>
</html>