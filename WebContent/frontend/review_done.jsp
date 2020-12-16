<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted - HT Bookstore</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<div align="center">
		<jsp:include page="header.jsp"/>
			<table class="form" width="50%";>
			
				<tr>
					<td><h3 style="margin-bottom: 0;">Your review</h3></td>
					<td>&nbsp;</td>
					<td align="right">
					<h3 style="margin-bottom: 0;">${loggedCustomer.person.fullName.firstName} ${loggedCustomer.person.fullName.lastName}</h3>
					</td>
				</tr> 
				
				<tr>
					<td colspan="3"><hr style="margin-top: 0;"/></td>
				</tr>
				
				<tr>
					<td>
						<h4 style="margin: 0 auto;">${item.product.name}</h4> <br/>
						<img src="data:image/jpg;base64, ${item.product.base64Image}" class="product-img-detail">
					</td>
				
					<td align="center">
						<h3>Your comment has been posted. Thank you!</h3>
					</td>
					
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<div align="center">
							<button style="width: 80px;" id ="btn-cancel" type="button">Go back</button>
						</div>
		
					</td>
				</tr>
			
			</table>
					
		<jsp:include page="footer.jsp"/>
	</div>
<script type="text/javascript">
	$(document).ready(function(){

		$('#btn-cancel').click(function() {
			history.go(-2);
		});
		
	});
</script>
</body>
</html>