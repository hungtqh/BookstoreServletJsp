<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Account - HT Store Administration</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<c:if test="${account != null}">
			<h1>Edit Account</h1>
		</c:if>
		<c:if test="${account == null}">
			<h2 class="page-heading">Create New Account</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${account == null}">
			<form action="create_user" method="post" id="user_form">
		</c:if>
		<c:if test="${account != null}">
			<form action="update_user" method="post" id="user_form"">
				<input type="hidden" name="accountId" value="${account.id}">
		</c:if>
		<table class="form">
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username"
					value="${account.username}"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" value="${user.password}"
					name="password"></td>
			</tr>
			<tr>
				<td>Account Type:</td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="radio" name="role" value="Seller"
					<c:if test="${account.role == 'Seller'}"> checked </c:if>>
					Seller</td>
			</tr>
	
			<tr>
				<td></td>
				<td><input type="radio" name="role" value="User"
					<c:if test="${account.role == 'User'}"> checked</c:if>>
					User</td>
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

	<script type="text/javascript">
		$(document).ready(function() {

			$('#user_form').validate({
				rules : {
					username : "required",
					password : "required",
					role : "required"
				},

				messages : {
					username : "Please enter user name",
					password : "Please enter password",
					role : "Please choose user type"
				}

			});

			$('#cancel_button').click(function() {
				history.go(-1);
			});
		});
	</script>
</body>
</html>