<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review - HT Store</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>

<link rel="stylesheet"
	href='<c:url value = "/css/jquery.rateyo.min.css" />'>

</head>
<body>
	<div align="center">
		<jsp:include page="header.jsp" />

		<form action="submit_review" method="post" id="review_form">
			<input type="hidden" name="itemID" value="${item.id}"> <input
				type="hidden" name="rating" id="rating">

			<table class="form" width="50%";>

				<tr>
					<td><h3 style="margin-bottom: 0;">Your review</h3></td>
					<td>&nbsp;</td>
					<td align="right">
						<h3 style="margin-bottom: 0;">${loggedCustomer.person.fullName.firstName}
							${loggedCustomer.person.fullName.lastName}</h3>
					</td>
				</tr>

				<tr>
					<td colspan="3"><hr style="margin-top: 0;" /></td>
				</tr>

				<tr>
					<td>
						<h4 style="margin: 0 auto;">${item.product.name}</h4> <br /> <img
						src="data:image/jpg;base64, ${item.product.base64Image}"
						class="product-img-detail">
					</td>

					<td>
						<div id="rateYo"></div> <br /> <input
						style="margin-bottom: 20px; width: 300px;" type="text"
						name="headline" placeholder="Healine for the review"> <textarea
							style="margin-bottom: 20px;" rows="13" cols="50" name="comment"
							placeholder="Write your review here"></textarea>

						<div align="center">
							<button style="width: 80px;" type="submit">Submit</button>
							&nbsp;&nbsp;&nbsp;
							<button style="width: 80px;" id="btn-cancel" type="button">Cancel</button>
						</div>
					</td>

				</tr>

			</table>

		</form>

		<jsp:include page="footer.jsp" />
	</div>

	<script type="text/javascript"
		src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.validate.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.rateyo.min.js" />'></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#review_form').validate({
				rules : {
					headline : "required",
					comment : "required"
				},

				messages : {
					headline : "Please enter headline!",
					comment : "Please enter comment!",
				}
			});

			$("#rateYo").rateYo({
				rating : 5,
				fullStar : true,
				starWidth : "40px",
				onInit : function(rating, rateYoInstance) {
					$('#rating').val(rating);
				},

				onSet : function(rating, rateYoInstance) {
					$('#rating').val(rating);
				}
			});

			$('#btn-cancel').click(function() {
				history.go(-1);
			});

		});
	</script>
</body>
</html>