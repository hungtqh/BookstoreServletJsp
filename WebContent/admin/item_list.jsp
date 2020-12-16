<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Items - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Items Management</h2>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
			<table>
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Image</th>
					<th class="product-title">Title</th>
					<th>Selling Price</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="item" items="${listItem}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${item.id}</td>
						<td>
							<img src="data:image/jpg;base64, ${item.product.base64Image}" style="width: 60px;" class="product-img-admin">
						</td>
						
						<td>${item.product.name}</td>
						<td>$ ${item.sellingPrice}</td>
						
						<td>
							<a href="edit_item?id=${item.id}">Edit</a> &nbsp;
							<a href="#" class="remove-link" id="${item.id}">Remove</a>  &nbsp;
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</main>
	<jsp:include page="footer.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('.remove-link').each(function(){
				$(this).on("click", function(){
					itemId = $(this).attr("id");
					if (confirm('Are you sure you want to remove the item with ID ' + itemId + ' ?')) {
						window.location = 'remove_item?id=' + itemId;
					}
				});
			});
		});
	</script>
</body>
</html>