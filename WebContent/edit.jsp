<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Item</title>
<style type="text/css">
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	fieldset { width:480px; border-radius:5px; }
	#edit-product-form label { display: block; float: left; width: 150px; text-align: right; clear: both; margin:3px; }
	#edit-product-form input[type=text] { display: block; float: right; width: 300px; margin: 3px;}
	#edit-product-form input[type=submit] { display: block; float: right; width:300px; margin: 3px; float:right; clear: both; }
</style>
</head>
<body>
	<div class="page">
		<fieldset>
			<legend>Fill out fields</legend>
			<form id="edit-product-form" method="post" action="/eshop/products">
				<input type="hidden" name="action" value="${attributes.action}"/>
				<input type="hidden" name="id" value="${product.id}" />
				<label for="name">Product name:</label>
				<input type="text" name="name" id="name" value="${product.name}" required />
				<label for="price">Price:</label>
				<input type="text" name="price" id="price" value="${product.price}" required />
				<input type="submit" value="add product" />
			</form>
		</fieldset>
	</div>	
</body>
</html>