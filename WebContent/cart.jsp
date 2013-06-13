<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<form method="post" enctype="multipart/form-data" action="products">
			File to upload: <input type="file" name="file"><br/> <br/>
		  	<input type="submit" value="Press"> to upload the file!
		  	<input type="hidden" name="action" value="fileupload"/>
		</form>
		<c:choose>
			<c:when test="${empty sessionScope.products_session_key}">
				<p>Shopping cart is empty</p>
			</c:when>
			<c:otherwise>
				<div class="entry">
					<div class="descr"><p><strong>Name</strong></p></div>
					<div class="descr"><p><strong>Price</strong></p></div>
					<div class="qty"><p><strong>Quantity</strong></p></div>
					<div class="clear"></div>
				</div>
				
				<c:set var="cartlines" value="${sessionScope.products_session_key}"/>
				<c:forEach items="${sessionScope.products_session_key}" var="line">
					<div class="entry">
						<div class="descr">
							<p>${line.product.name}</p>
						</div>
						<div class="descr">
							<p>$${line.price}</p>
						</div>
						<div class="qty">
							<p>${line.quantity}</p>
						</div>
						<div class="clear"></div>
					</div>
				</c:forEach>		
				<div class="entry">
					<div class="descr"><p><strong>Total:</strong></p></div>
					<div class="descr"><p><strong>$to be defined</strong></p></div>
					<div class="qty"><p></p></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</c:otherwise>
		</c:choose>
		
		<div class="buttons">
			<form action="products">
				<input type="submit" value="continue shopping" class="cart-button" />
			</form>
			<form action="checkout.jsp">
				<input type="submit" value="checkout" class="cart-button" />
			</form>
		</div>
	</div>
</body>
</html>