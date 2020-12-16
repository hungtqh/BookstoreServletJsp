<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HT Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h2 class="page-heading">Administrative DashBoard</h2>
		</div>
		<hr width=55%>
		
		<div align="center" style="margin-bottom:35px">
			<h2 class="page-heading">Quick Actions:</h2>
			<b>
				<a href="new_product">New Product</a> &nbsp;
				<a href="user_form.jsp">New Account</a> &nbsp;
				<a href="category_form.jsp">New Category</a> &nbsp;
				<a href="new_customer">New Customer</a>
			</b>	
		</div>
		<hr width=55%>
		
		<div align="center">
			<h2 class="page-heading">Recent Sales:</h2>
		</div>
		<hr width=55%>
		
		<div align="center">
			<h2 class="page-heading">Recent Reviews:</h2>
		<c:if test="${listNewReview != null and listNewReview.size() > 0}">
			<div align="center" style="width:55%;">
			<font size="2">
			<table>
				<tr>
					<th>ID</th>
					<th>Item</th>
					<th>Rating</th>
					<th>Headline</th>
					<th>Customer</th>
					<th>Review On</th>
				</tr>
				<c:forEach var="review" items="${listNewReview}">
					<tr>
						<td><a href="edit_review?id=${review.id}">${review.id}</a></td>
						<td>${review.item.product.name}</td>
						<td>${review.rating}</td>
						<td>${review.headline}</td>
						<td>${review.customer.person.fullName.firstName} ${review.customer.person.fullName.lastName}</td>
						<td><fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss z' value='${review.reviewTime}'/></td>
					</tr>
				</c:forEach>
			</table>
			</font>
			</div>
		</c:if>
		</div>
		<hr width=55%>
		
		<div align="center">
			<h2 class="page-heading">Statistics:</h2>
			<span><b>Total Accounts: ${totalAccounts}</b></span>  &nbsp;&nbsp;
			<span><b>Total Customers: ${totalCustomers}</b></span> &nbsp;&nbsp;
			<span><b>Total Products: ${totalProducts}</b></span> &nbsp;&nbsp;
			<span><b>Total Items Available: ${totalItems}</b></span> &nbsp;&nbsp;
			<span><b>Total Reviews: ${totalReviews}</b></span> &nbsp;&nbsp;
		</div>
		<hr width=55%>
	</main>
	<jsp:include page="footer.jsp"/>
</body>
</html>