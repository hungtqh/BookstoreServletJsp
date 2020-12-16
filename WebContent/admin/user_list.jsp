<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Accounts - HT Book Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Accounts Management</h2>
			<h3><a href="user_form.jsp">Create New Account</a></h3>
		</div>
		
		<div align="center">
			<h4 class="message">${message}</h4>	
		</div>
		
		<div align="center">
			<table>
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Username</th>
					<th>Account Type</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="account" items="${accounts}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${account.id}</td>
						<td>${account.username}</td>
						<td>${account.role}</td>
						<td>
							<a href="edit_user?id=${account.id}">Edit</a> &nbsp;
							<a href="#" class="delete-link" id="${account.id}">Delete</a>
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
					accountId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the account with ID ' + accountId + ' ?')) {
						window.location = 'delete_user?id=' + accountId;
					}
				});
			});
		});
	</script>
</body>
</html>