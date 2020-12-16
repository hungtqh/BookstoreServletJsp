<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${category.name} - HT Store</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
	<div align="center">
		<h2>${category.name}</h2>
	</div>
	<div align="left" style="margin-left: 15%;">
		<c:forEach items="${listItem}" var="item">
			<div class="product-infor" align="center">
				<div>
					<a href="view_item?id=${item.id}"> <img
						src="data:image/jpg;base64, ${item.product.base64Image}"
						class="product-img">
					</a>
				</div>
				<div class="product-detail">
					<div>
						<a href="view_item?id=${item.id}"> <b>${item.product.name}</b></a>
					</div>

					<div>
						<jsp:directive.include file="item_rating.jsp" />
					</div>

					<div>
						<b>$ ${item.sellingPrice}</b>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	</main>
	<jsp:include page="footer.jsp" />
	
	<script type="text/javascript"
		src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
	<script type="text/javascript"
		src='<c:url value = "/js/jquery.validate.min.js" />'></script>
</body>
</html>