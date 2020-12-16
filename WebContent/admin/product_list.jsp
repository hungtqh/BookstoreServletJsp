<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Products - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Products Management</h2>
			<h3><a href="new_product">Create New Product</a></h3>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
		<div style="margin: 30px 30px;">
			
			<form action="list_product_by_category" method="get">
				<span>Category: </span> &nbsp;&nbsp;&nbsp;
				<select name="category_id">
							<c:forEach items="${listCategory}" var="category">
								<option value="${category.id}">
									${category.name}
								</option>
							</c:forEach>
				</select> 
				&nbsp;&nbsp;&nbsp;
				<button type="submit">Filter</button>
			</form>
		</div>	
			<table>
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Image</th>
					<th class="product-name">Name</th>
					<th class="product-units">Units In Stock</th>
					<th>Category</th>
					<th>Price</th>
					<th>Last Updated</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="product" items="${listProduct}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${product.id}</td>
						<td>
							<img src="data:image/jpg;base64, ${product.base64Image}" style="width: 60px;" class="product-image-admin">
						</td>
						<td width="250px">${product.name}</td>
						<td>${product.unitsInStock}</td>
						<td>${product.category.name}</td>
						<td>$ ${product.price}</td>
						<td><fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss z' value='${product.lastUpdatedTime}'/></td>
						<td>
							<a href="edit_product?id=${product.id}">Edit</a> &nbsp;
							<a href="#" class="delete-link" id="${product.id}">Delete</a>  &nbsp;
							<c:if test="${!product.active}"><a href="sell_product?id=${product.id}">Sell</a> </c:if>
							<c:if test="${product.active}"><a href="view_item">View Item</a> </c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</main>
	<jsp:include page="footer.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('.delete-link').each(function(){
				$(this).on("click", function(){
					productId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the product with ID ' + productId + ' ?')) {
						window.location = 'delete_product?id=' + productId;
					}
				});
			});
		});
	</script>
</body>
</html>