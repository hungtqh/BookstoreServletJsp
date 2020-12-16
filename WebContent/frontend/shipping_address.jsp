<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customer Shipping Address - HT Store</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Your Shipping Address</h2>
			<h3><a href="new_address">Create New Address</a></h3>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
			<table>
				<tr>
					<th>No.</th>
					<th>Recipient Name</th>
					<th>Recipient Phone</th>
					<th>Province/City</th>
					<th>District/Town</th>
					<th>Address Detail</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="address" items="${listAddress}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${address.recipientName}</td>
						<td>${address.recipientPhone}</td>
						<td>${address.province}</td>
						<td>${address.city}</td>
						<td>${address.addressDetail}</td>
						<td>
							<a href="edit_address?id=${address.id}">Edit</a> &nbsp;
							<a href="#" class="delete-link" id="delete_address?id=${address.id}">Delete</a>
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
					deleteLink = $(this).attr("id");
					if (confirm('Are you sure want to delete this address ?')) {
						window.location = deleteLink;
					}
				});
			});
		});
	</script>
</body>
</html>