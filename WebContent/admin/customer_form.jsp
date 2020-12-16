<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer- HT Store Administration</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
<script type="text/javascript"
	src='<c:url value = "/js/jquery.validate.min.js" />'></script>
<script type="text/javascript"
	src='<c:url value = "/js/jquery.richtext.min.js" />'></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href='<c:url value = "/css/richtext.min.css" />'>

</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<c:if test="${customer != null}">
			<h2 class="page-heading">Edit Customer</h2>
		</c:if>
		<c:if test="${customer == null}">
			<h2 class="page-heading">Create Customer</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" id="customer_form">
		</c:if>
		<c:if test="${customer != null}">
			<form action="update_customer" method="post" id="customer_form">
				<input type="hidden" name="customerID" value="${customer.id}">
		</c:if>
		<table class="form customer-form">
			<c:if test="${customer == null}">
				<tr>
					<td>Account (*):</td>
					<td><select name="account">
							<c:forEach items="${listAccount}" var="account">
								<option value="${account.id}"
									<c:if test="${account.id eq customer.person.account.id}">selected</c:if>>
									${account.username}</option>
							</c:forEach>
					</select></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2"><hr /></td>
			</tr>

			<tr>
				<td>First Name (*):</td>
				<td><input type="text" name="firstName" class="input"
					value="${customer.person.fullName.firstName}"></td>
			</tr>
			<tr>
				<td>Last Name (*):</td>
				<td><input type="text" name="lastName" class="input"
					value="${customer.person.fullName.lastName}"></td>
			</tr>
			<tr>
				<td>Date Of Birth:</td>
				<td><input type="text" name="dateOfBirth" id="dateOfBirth"
					class="input"
					value="<fmt:formatDate pattern='MM/dd/yyyy' value='${customer.person.dateOfBirth}'/>">
				</td>
			</tr>

			<tr>
				<td>Gender:</td>
				<td><input type="radio" name="gender" value="Male"
					<c:if test="${customer.person.gender == 'Male'}"> checked </c:if>>
					Male <input type="radio" name="gender" value="Female"
					<c:if test="${customer.person.gender == 'Female'}"> checked</c:if>>
					Female</td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="text" value="${customer.person.email}"
					class="input" name="email"></td>
			</tr>

			<tr>
				<td>Phone Number:</td>
				<td><input type="text" value="${customer.person.phone}"
					class="input" name="phone"></td>
			</tr>

			<tr>
				<td>Province/City:</td>
				<td><input type="text" name="province" class="input"
					value="${customer.person.address.province}"></td>
			</tr>
			<tr>
				<td>District/Town:</td>
				<td><input class="input" type="text"
					value="${customer.person.address.city}" name="city"></td>
			</tr>
			<tr>
				<td>Address Detail:</td>
				<td><input type="text" class="input"
					value="${customer.person.address.addressDetail}" name="address"></td>
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

			$(function() {
				$("#dateOfBirth").datepicker({
					changeMonth : true,
					changeYear : true
				});
			});

			$('#customer_form').validate({
				rules : {
					account : "required",
					firstName : "required",
					lastName : "required"
				},

				messages : {
					account : "Please choose a account or create a new one.",
					firstName : "Please enter first name.",
					lastName : "Please enter last name."
				}

			});

			$('#cancel_button').click(function() {
				history.go(-1);
			});
		});
	</script>
</body>
</html>