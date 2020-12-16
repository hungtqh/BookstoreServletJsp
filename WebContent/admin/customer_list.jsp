<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Customers - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Customers Management</h2>
			<h3><a href="new_customer">Create New Customer</a></h3>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
			<table>
				<tr>
					<th>Index</th>
					<th>ID</th>  
					<th>Full Name</th>
					<th>Username</th>
					<th>Province/City</th>
					<th>District/Town</th>
					<th>Registered Date</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="customer" items="${listCustomer}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${customer.id}</td>
						<td>${customer.person.fullName.firstName} ${customer.person.fullName.lastName}</td>
						<td>${customer.person.account.username}</td>
						<td>${customer.person.address.province}</td>
						<td>${customer.person.address.city}</td>
						<td><fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss z' value='${customer.registerDate}'/></td>
						<td>
							<a href="view_address?id=${customer.id}">Shipping Address</a> &nbsp;
							<a href="edit_customer?id=${customer.id}">Edit</a> &nbsp;
							<a href="#" class="delete-link" id="${customer.id}">Delete</a>
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
					customerId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the customer with ID ' + customerId + ' ?')) {
						window.location = 'delete_customer?id=' + customerId;
					}
				});
			});
		});
	</script>
</body>
</html>