<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HT Store - Online Store</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
	<div align="center">
		<h2>New Products:</h2>
		<c:forEach items="${newItems}" var="item">
			<div style="width: 15%;" class="new-product-infor">
				<div>
					<a href="view_item?id=${item.id}"> <img
						src="data:image/jpg;base64, ${item.product.base64Image}" class="product-img">
					</a>
				</div>
				<div class="product-detail">
					<div>
						<a href="view_item?id=${item.id}"> <b>${item.product.name}</b></a>
					</div>
					<div>
						<jsp:directive.include file = "item_rating.jsp" />		
					</div>
					<div>
						<b>$ ${item.product.price}</b>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<h2>Most-favored Products:</h2>
		<c:forEach items="${favouriteItems}" var="item">
			<div style="width: 15%;" class="new-product-infor">
				<div>
					<a href="view_item?id=${item.id}"> <img
						src="data:image/jpg;base64, ${item.product.base64Image}" class="product-img">
					</a>
				</div>
				<div class="product-detail">
					<div>
						<a href="view_item?id=${item.id}"> <b>${item.product.name}</b></a>
					</div>
					<div>
						<jsp:directive.include file = "item_rating.jsp" />		
					</div>
					<div>
						<b>$ ${item.product.price}</b>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<div align="center" style="clear: both">
			<h2>Best-Selling Products:</h2>
		</div>
	</div>
	</main>
	<jsp:include page="footer.jsp" />	
</body>
</html>