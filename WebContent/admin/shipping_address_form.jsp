<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Shipping Address - HT Store Administration</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>

</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<h2 class="page-heading">Edit Customer Shipping Address</h2>
	</div>

	<div align="center">
		<form action="update_address" method="post" id="address_form">
			<input type="hidden" name="addressId" value="${address.id}">
			<input type="hidden" name="customerId" value="${address.customer.id}">
			<table class="form">
				<tr>
					<td>Recipient Name:</td>
					<td><input type="text" name="recipientName"
						value="${address.recipientName}"></td>
				</tr>
				<tr>
					<td>Recipient Phone:</td>
					<td><input type="text" name="recipientPhone"
						value="${address.recipientPhone}"></td>
				</tr>

				<tr>
					<td>Province/City:</td>
					<td><input type="text" name="province"
						value="${address.province}"></td>
				</tr>

				<tr>
					<td>District/Town:</td>
					<td><input type="text" name="city" value="${address.city}"></td>
				</tr>


				<tr>
					<td>Address Detail:</td>
					<td><input type="text" name="addressDetail"
						value="${address.addressDetail}"></td>
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
			$('#address_form').validate({
				rules : {
					recipientName : "required",
					recipientPhone : "required",
					city : "required",
					address : "required",
					country : "required"
				},

				messages : {
					recipientName : "Please enter recipient name!",
					recipientPhone : "Please enter recipient phone!",
					city : "Please enter city!",
					address : "Please enter address!",
					country : "Please enter country!"
				}
			});

			$('#cancel_button').click(function() {
				history.go(-1);
			});
		});
	</script>
</body>
</html>