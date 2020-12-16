<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Item - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.richtext.min.js" />' ></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href='<c:url value = "/css/richtext.min.css" />' >
	
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<c:if test="${item != null}">
			<h1>Edit Item</h1>
		</c:if>
		<c:if test="${item == null}">
			<h2 class="page-heading">Create New Item</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${item == null}">
			<form action="create_item" method="post" id="item_form" enctype="multipart/form-data">
			<input type="hidden" name="productId" value="${product.id}">
		</c:if>
		<c:if test="${item != null}">
			<form action="update_item" method="post" id="item_form" enctype="multipart/form-data">
			<input type="hidden" name="itemId" value="${item.id}">
		</c:if>
			
		<table class="form form-product"> 
			<tr>
				<td>Category:</td>
				<td>
					 <c:if test="${item == null}">${product.category.name}</c:if>	
					 <c:if test="${item != null}">${item.product.category.name}</c:if>				
				</td>
			</tr>
		
			<tr>
				<td>Title:</td>
				<td>
					<c:if test="${item == null}">${product.name}</c:if>	
					<c:if test="${item != null}">${item.product.name}</c:if>	
				</td>
			</tr>
			<tr>
				<td>Units In Stock:</td>
				<td>
					<c:if test="${item == null}">${product.unitsInStock}</c:if>
					<c:if test="${item != null}">${item.product.unitsInStock}</c:if>	
				</td>
			</tr>
		
			<tr>
				<td>Image:</td>
				<td>
					<img id="thumbnail" alt="Image Review" style="width: 120px; margin-top: 10px;"
					src="data:image/jpg;base64, <c:if test="${item == null}">${product.base64Image}</c:if>
					<c:if test="${item != null}">${item.product.base64Image}</c:if>" >
				</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td>
					<c:if test="${item == null}">$ ${product.price}</c:if>
					<c:if test="${item != null}">${item.product.price}</c:if>	
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><textarea readonly="readonly" style="border: 0;" rows="10" cols="100"  name="description" id="description"><c:if test="${item == null}">${product.description}</c:if><c:if test="${item != null}">${item.product.description}</c:if></textarea></td>
			</tr>
			<tr>
				<td>Selling price: </td>
				<td>
					<input type="text" name="selling_price" 
					value="${item.sellingPrice}">
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type="submit">Save</button>  &nbsp; &nbsp;
				<button type="button" id="cancel_button">Cancel</button>
				<td>
			</tr>
		</table>
		</form>
	</div>

	</main>
	<jsp:include page="footer.jsp" />
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#product_image").change(function() {
			showImageThumbnail(this);
		});
		
	   $('#item_form').validate({
	       rules: {
	    	   selling_price: "required"
	       },
	       
	       messages: {
	    	   selling_price: "Choose a selling price for the product."			
	       }
	 
	    });
	   
	   $('#cancel_button').click(function(){
		   history.go(-1); 
	   });
	});
	
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#thumbnail").attr('src', e.target.result);
		}
		
		reader.readAsDataURL(file);
	}
</script>
</body>
</html>