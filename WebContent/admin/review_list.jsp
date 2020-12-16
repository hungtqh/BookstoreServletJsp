<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Reviews - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Review Management</h2>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
			<table>
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Item</th>
					<th>Rating</th>
					<th>Headline</th>
					<th>Customer</th>
					<th>Review On</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="review" items="${listReview}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${review.id}</td>
						<td>${review.item.product.name}</td>
						<td>${review.rating}</td>
						<td>${review.headline}</td>
						<td>${review.customer.person.fullName.firstName} ${review.customer.person.fullName.lastName}</td>
						<td><fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss z' value='${review.reviewTime}'/></td>
						<td>
							<a href="edit_review?id=${review.id}">Edit</a> &nbsp;
							<a href="#" class="delete-link" id="${review.id}">Delete</a>
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
					reviewId = $(this).attr("id");
					if (confirm('Are you sure want to delete the review with ID '  + reviewId + ' ?')) {
						window.location = "delete_review?id=" + reviewId;
					}
				});
			});
		});
	</script>
</body>
</html>