<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile - HT Book Store</title>
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
		<h2 class="page-heading">Edit My Profile</h2>
		<div>
			<h4 class="message">${message}</h4>
		</div>
	</div>

	<div align="center">

		<form action="update_profile" method="post" id="customer_form">
			<table class="form customer-form">

				<tr>
					<td>Username:</td>
					<td>${loggedCustomer.person.account.username}</td>
				</tr>

				<tr>
					<td>First Name (*):</td>
					<td><input type="text" name="firstName" class="input"
						value="${loggedCustomer.person.fullName.firstName}">
					</td>
				</tr>
				<tr>
					<td>Last Name (*):</td>
					<td><input type="text" name="lastName" class="input"
						value="${loggedCustomer.person.fullName.lastName}">
					</td>
				</tr>
				<tr>
					<td>Date Of Birth:</td>
					<td><input type="text" name="dateOfBirth" id="dateOfBirth" class="input"
						value="<fmt:formatDate pattern='MM/dd/yyyy' value='${loggedCustomer.person.dateOfBirth}'/>">
					</td>
				</tr>

				<tr>
					<td>Gender:</td>
					<td><input type="radio" name="gender" value="Male"
						<c:if test="${loggedCustomer.person.gender == 'Male'}"> checked </c:if>>
						Male 
						<input type="radio" name="gender" value="Female"
						<c:if test="${loggedCustomer.person.gender == 'Female'}"> checked</c:if>>
						Female</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" class="input"
						value="${loggedCustomer.person.email}"></td>
				</tr>

				<tr>
					<td>Phone Number:</td>
					<td><input type="text" name="phone" class="input"
						value="${loggedCustomer.person.phone}"></td>
				</tr>
				
				<tr>
					<td>Province/City:</td>
					<td><input type="text" name="province" class="input"
						value="${loggedCustomer.person.address.province}">
					</td>
				</tr>
				
				<tr>
					<td>District/Town:</td>
					<td><input type="text" name="city" class="input"
						value="${loggedCustomer.person.address.city}"></td>
				</tr>
				
				<tr>
					<td>Address Detail:</td>
					<td><input type="text" name="addressDetail" class="input"
						value="${loggedCustomer.person.address.addressDetail}">
					</td>
				</tr>

				<tr>
					<td colspan="2"><hr /></td>
				</tr>

				<tr>
					<td colspan="2"><i>(Leave the fields blank if you don't
							want to change password)</i></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>Old Password (*):</td>
					<td><input class="input" type="password" name="oldPassword" id="oldPassword"> 
					</td>
				</tr>

				<tr>
					<td>New Password (*):</td>
					<td><input class="input" type="password" name="password" id="password">
					</td>
				</tr>

				<tr>
					<td>Confirm Password (*):</td>
					<td><input class="input" type="password" name="confirmPassword">
					</td>
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
					firstName : "required",
					lastName : "required",
					confirmPassword : {
						equalTo : "#password"
					}
				},

				messages : {
					firstName : "Please enter first name.",
					lastName : "Please enter last name.",
					confirmPassword : {
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