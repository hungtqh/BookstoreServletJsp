<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Category - HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<c:if test="${category != null}">
			<h2 class="page-heading">Edit Category</h2>
		</c:if>
		<c:if test="${category == null}">
			<h2 class="page-heading">Create New Category</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${category == null}">
			<form action="create_category" method="post" id="category_form">
		</c:if>
		<c:if test="${category != null}">
			<form action="update_category" method="post" id="category_form">
				<input type="hidden" name="categoryId"
					value="${category.id}">
		</c:if>
		<table class="form">
			<tr>
				<td>Name:</td>
				<td><input type="text" name="categoryName" value="${category.name}"></td>
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

<script type="text/javascript">
	$(document).ready(function(){
		$('#category_form').validate({
			rules: {
				categoryName: "required",
			},
			
			messages: {
				categoryName: "Please enter category name!",
			}
		});
		
		$('#cancel_button').click(function(){
			   history.go(-1); 
		   });
	});
</script>
</body>
</html>