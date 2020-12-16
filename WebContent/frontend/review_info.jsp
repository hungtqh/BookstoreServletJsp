<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review - HT Bookstore</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<link rel="stylesheet"
	href='<c:url value = "/css/jquery.rateyo.min.css" />'>

</head>
<body>
	<div align="center">
		<jsp:include page="header.jsp" />

		<table class="form" width="50%";>

			<tr>
				<td><h3 style="margin-bottom: 0;">See your review</h3></td>
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
					style="border: 0; margin-bottom: 20px; width: 300px;" type="text"
					name="headline" readonly="readonly" value="${review.headline}">

					<textarea style="border: 0; margin-bottom: 20px;" rows="13"
						cols="50" name="comment" readonly="readonly">${review.comment}</textarea>

					<div align="center">
						<button style="width: 80px;" id="btn-cancel" type="button">Go
							back</button>
					</div>
				</td>

			</tr>

		</table>

		<jsp:include page="footer.jsp" />
	</div>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.validate.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.rateyo.min.js" />'></script>
	<script type="text/javascript">
	$(document).ready(function(){
		
		$("#rateYo").rateYo({
			rating: ${review.rating},
		    fullStar: true,
			readOnly: true   
		  });
		
		$('#btn-cancel').click(function() {
			history.go(-1);
		});
		
	});
</script>
</body>
</html>