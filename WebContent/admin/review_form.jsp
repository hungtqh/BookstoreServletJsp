<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Review - HT Book Store Administration</title>
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
		<h2 class="page-heading">Edit Review</h2>
	</div>

	<div align="center">
		<form action="update_review" method="post" id="review_form">
			<input type="hidden" name="reviewId" value="${review.id}">
			<table class="form">
				<tr>
					<td>Book:</td>
					<td><b>${review.item.product.name}</b></td>
				</tr>
				<tr>
					<td>Rating:</td>
					<td><b>${review.rating}</b></td>
				</tr>
				<tr>
					<td>Customer:</td>
					<td><b>${review.customer.person.fullName.firstName} ${review.customer.person.fullName.lastName}</b></td>
				</tr>
			
				<tr>
					<td>Headline:</td>
					<td><input style="width: 300px;" type="text" name="headline" value="${review.headline}"></td>
				</tr>
				<tr>
					<td>Comment:</td>
					<td><textarea rows="5" cols="70" name="comment">${review.comment}</textarea></td>
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
		$(document).ready(function() {
			$('#review_form').validate({
				rules : {
					headline : "required",
					comment: "required"
				},

				messages : {
					headline : "Please enter headline!",
					comment: "Please enter comment!"
				}
			});

			$('#cancel_button').click(function() {
				history.go(-1);
			});
		});
	</script>
</body>
</html>