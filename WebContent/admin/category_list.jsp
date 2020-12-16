<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Categories - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Category Management</h2>
			<h3><a href="category_form.jsp">Create New Category</a></h3>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
			<table>
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Name</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="category" items="${listCategory}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${category.id}</td>
						<td>${category.name}</td>
						<td>
							<a href="edit_category?id=${category.id}">Edit</a> &nbsp;
							<a href="#" class="delete-link" id="${category.id}">Delete</a>
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
					categoryId = $(this).attr("id");
					if (confirm('Are you sure want to delete the category with ID '  + categoryId + ' ?')) {
						window.location = "delete_category?id=" + categoryId;
					}
				});
			});
		});
	</script>
</body>
</html>