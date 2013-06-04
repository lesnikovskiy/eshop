<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>   
<%@ page session="true" import="java.util.List, java.util.ArrayList, eshop.model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<style type="text/css">
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	.entry { border:1px solid #333; border-radius:5px; padding:10px 15px; } 
	.page .descr {float:left; width:300px;}
	.page .buttons { float: right; width: 200px; }
	.clear { clear:both; }
</style>
</head>
<body>
	<div class="page">
	<h3>Products</h3>
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
						</div>
						<div class="buttons">
							<p class="add-to-cart">
								<form method="post" action="/eshop/products">
									<input type="hidden" name="action" value="addToCart" />
									<input type="submit" value="add to cart" />
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
		<a href="/eshop/edit.jsp">Add new product</a>
	</div>
	</div>
</body>
</html>