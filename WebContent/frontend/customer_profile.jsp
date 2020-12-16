<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile - HT Book Store Administration</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<script type="text/javascript"
	src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
<script type="text/javascript"
	src='<c:url value = "/js/jquery.validate.min.js" />'></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
	<div align="center">
		<div>
			<h2 class="page-heading">Customer profile</h2>
			<div>
				<h4 class="message">${message}</h4>
			</div>
		</div>

		<table class="form">

			<tr>
				<td><b>Username:</b></td>
				<td>${loggedCustomer.person.account.username}</td>
			</tr>

			<tr>
				<td><b>First Name:</b></td>
				<td>${loggedCustomer.person.fullName.firstName}</td>
			</tr>
			<tr>
				<td><b>Last Name:</b></td>
				<td>${loggedCustomer.person.fullName.lastName}</td>
			</tr>

			<tr>
				<td><b>Date Of Birth:</b></td>
				<td><fmt:formatDate pattern='dd/MM/yyyy'
						value='${loggedCustomer.person.dateOfBirth}' /></td>
			</tr>
			
			<tr>
				<td><b>Gender:</b></td>
				<td>${loggedCustomer.person.gender}</td>
			</tr>

			<tr>
				<td><b>Email:</b></td>
				<td>${loggedCustomer.person.email}</td>
			</tr>

			<tr>
				<td><b>Phone Number:</b></td>
				<td>${loggedCustomer.person.phone}</td>
			</tr>

			<tr>
				<td><b>Province/City:</b></td>
				<td>${loggedCustomer.person.address.province}</td>
			</tr>
			<tr>
				<td><b>District/Town:</b></td>
				<td>${loggedCustomer.person.address.city}</td>
			</tr>

			<tr>
				<td><b>Address Detail:</b></td>
				<td>${loggedCustomer.person.address.addressDetail}</td>
			</tr>

		</table>

		<div>
			<h3>
				<a href="edit_profile">Edit My Profile</a>
			</h3>
		</div>


	</div>
	</main>
	<jsp:include page="footer.jsp" />
</body>
</html>