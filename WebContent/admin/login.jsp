<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HT Store Admin Login</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<div align="center">
		<h1>HT Store Administration</h1>
		<h2>Admin Login</h2>
		<h4 class="message">${message}</h4>	
		<form id="login_form" action="login" method="post" class="form">
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" id="username"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><button type="submit">Login</button>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#login_form').validate({
			rules: {
				username: "required",
				password: "required"
			},
			
			messages: {
				username: "Please enter username!",
				password: "Please enter password!"
			}
		});
	});
</script>
</body>
</html>