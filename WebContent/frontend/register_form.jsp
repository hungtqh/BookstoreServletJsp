<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - HT Store</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href='<c:url value = "/css/richtext.min.css" />'>

</head>
<body>
	<jsp:include page="header.jsp" />
	<main>

	<div align="center">
		<h2 class="page-heading">Register</h2>
	</div>

	<div align="center">

		<form action="register_customer" method="post" id="customer_form">
			<table class="form customer-form">

				<tr>
					<td>Username (*):</td>
					<td><input class="input" type="text" name="username"></td>
				</tr>

				<tr>
					<td>Password (*):</td>
					<td><input class="input" type="password" name="password"
						id="password"></td>
				</tr>

				<tr>
					<td>Confirm Password (*):</td>
					<td><input class="input" type="password"
						name="confirmPassword"></td>
				</tr>

				<tr>
					<td colspan="2"><hr /></td>
				</tr>

				<tr>
					<td>First Name (*):</td>
					<td><input class="input" type="text" name="firstName"></td>
				</tr>
				<tr>
					<td>Last Name (*):</td>
					<td><input class="input" type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Date Of Birth:</td>
					<td><input class="input" type="text" name="dateOfBirth"
						id="dateOfBirth"></td>
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
					<td><input class="input" type="text" name="email"></td>
				</tr>

				<tr>
					<td>Phone Number:</td>
					<td><input class="input" type="text" name="phone"></td>
				</tr>

				<tr>
					<td>Province/City:</td>
					<td><input class="input" type="text" name="province"></td>
				</tr>
				<tr>
					<td>District/Town:</td>
					<td><input class="input" type="text" name="city"></td>
				</tr>
				<tr>
					<td>Address Detail:</td>
					<td><input class="input" type="text" name="addressDetail"></td>
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
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.richtext.min.js" />'></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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
					username : "required",
					firstName : "required",
					lastName : "required",
					password : "required",
					confirmPassword : {
						required : true,
						equalTo : "#password"
					}
				},

				messages : {
					username : "Please enter username.",
					firstName : "Please enter first name.",
					lastName : "Please enter last name.",
					password : "Please enter password.",
					confirmPassword : {
						required : "Please confirm password.",
						equalTo : "Password does not match."
					}
				}

			});

			$('#cancel_button').click(function() {
				history.go(-1);
			});
		});
	</script>
</body>
</html>