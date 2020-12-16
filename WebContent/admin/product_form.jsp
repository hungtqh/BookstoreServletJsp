<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product - HT Store Administration</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href='<c:url value = "/css/richtext.min.css" />'>

</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<c:if test="${product != null}">
			<h1>Edit Product</h1>
		</c:if>
		<c:if test="${product == null}">
			<h2 class="page-heading">Create New Product</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${product == null}">
			<form action="create_product" method="post" id="product_form"
				enctype="multipart/form-data">
		</c:if>
		<c:if test="${product != null}">
			<form action="update_product" method="post" id="product_form"
				enctype="multipart/form-data">
				<input type="hidden" name="productId" value="${product.id}">
		</c:if>
		<table class="form form-product">
			<tr>
				<td>Category:</td>
				<td><select id="category" name="category">
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.id}"
								<c:if test="${category.id eq product.category.id}">selected</c:if>>
								${category.name}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" value="${product.name}"></td>
			</tr>
			<tr>
				<td>Units In Stock:</td>
				<td><input type="text" name="unitsInStock"
					value="${product.unitsInStock}"></td>
			</tr>

			<tr>
				<td>Image:</td>
				<td><input type="file" id="product_image" name="product_image"
					accept="image/*"> <br /> <img id="thumbnail"
					alt="Image Review" style="width: 120px; margin-top: 10px;"
					src="data:image/jpg;base64, ${product.base64Image}"></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input type="text" value="${product.price}" id="price"
					name="price"></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><textarea rows="15" cols="100"
						name="description" id="description">${product.description}</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button> &nbsp; &nbsp;
					<button type="button" id="cancel_button">Cancel</button>
				<td>
			</tr>
		</table>
		</form>
	</div>

	</main>
	<jsp:include page="footer.jsp" />

	<script type="text/javascript"
		src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.validate.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.richtext.min.js" />'></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){

		$( function() {
		    $( "#publish_date" ).datepicker({
		      changeMonth: true,
		      changeYear: true
		    });
		  } );
		
		$('#description').richText();
		
		$("#product_image").change(function() {
			showImageThumbnail(this);
		});
		
	   $('#product_form').validate({
	       rules: {
	    	   category: "required",
	    	   name: "required",
	    	   <c:if test="${product == null}">
	    	   product_image: "required",
	    	   </c:if>
	    	   price: "required",
	    	   unitsInStocks: "required",
	    	   description: "required"
	       },
	       
	       messages: {
	    	   category: "Select a category",
	    	   name: "Enter product name",
	    	   product_image: "Choose product image",
	    	   price: "Enter price",
	    	   unitsInStock: "Enter units in stock",
	    	   description: "Enter description"
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