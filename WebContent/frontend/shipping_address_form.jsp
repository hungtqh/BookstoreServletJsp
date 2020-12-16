<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Shipping Address - HT Store</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<c:if test="${address == null}">
				<h2 class="page-heading">Create Shipping Address</h2>
		</c:if>		
		<c:if test="${address != null}">
				<h2 class="page-heading">Update Shipping Address</h2>
		</c:if>	
	</div>

	<div align="center">
		<c:if test="${address == null}">
				<form action="create_address" method="post" id="address_form">
		</c:if>		
		<c:if test="${address != null}">
				<form action="update_address" method="post" id="address_form">
				<input type="hidden" name="addressId" value="${address.id}" >
		</c:if>	

		<table class="form">
			<tr>
				<td>Recipient Name:</td>
				<td><input type="text" name="recipientName" value="${address.recipientName}"></td>
			</tr>
			<tr>
				<td>Recipient Phone:</td>
				<td><input type="text" name="recipientPhone" value="${address.recipientPhone}"></td>
			</tr>
			
			<tr>
				<td>Province/City:</td>
				<td><input type="text" name="province" value="${address.province}"></td>
			</tr>
			<tr>
				<td>District/Town:</td>
				<td><input type="text" name="city" value="${address.city}"></td>
			</tr>
			
			<tr>
				<td>Address Detail:</td>
				<td><input type="text" name="addressDetail" value="${address.addressDetail}"></td>
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
		$('#address_form').validate({
			rules: {
				recipientName: "required",
				recipientPhone: "required",
				city: "required",
				address: "required",
				province: "required"
			},
			
			messages: {
				recipientName: "Please enter recipient name!",
				recipientPhone: "Please enter recipient phone!",
				city: "Please enter city!",
				address: "Please enter address!",
				province: "Please enter province!"
			}
		});
		
		$('#cancel_button').click(function(){
			   history.go(-1); 
		   });
	});
</script>
</body>
</html>