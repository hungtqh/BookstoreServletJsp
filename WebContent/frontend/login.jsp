<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login - HT Bookstore</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<div align="center">
	<jsp:include page="header.jsp"/>
		<h3>Customer Login</h3>
		<h4 class="message">${message}</h4>	
		<form id="login_form" action="login" method="post" class="form">
			<table>
				<tr>
					<td>Username: </td>
					<td><input type="text" name="username" id="username"></td>
				</tr>
				<tr>
					<td>Password: </td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><button type="submit">Login</button>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"/>
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