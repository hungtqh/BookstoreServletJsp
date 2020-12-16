<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results for ${keyword} - HT Store</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />'></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
	<div align="center">
		<c:if test="${message != null}">
			<h2>${message}</h2>
		</c:if>

		<c:if test="${message == null}">
			<h2>Search results for ${keyword}:</h2>

			<c:forEach items="${searchResult}" var="item">
				<div align="left"
					style="width: 80%; margin-left: 12%; margin-right: 5%;">

					<div style="display: inline-block; margin: 20px; width: 12%;">
						<a href="view_item?id=${item.id}"> <img
							src="data:image/jpg;base64, ${item.product.base64Image}" class="product-img">
						</a>
					</div>

					<div
						style="display: inline-block; width: 58%; margin: 20px; vertical-align: top;">
						<a href="view_item?id=${item.id}"><b>${item.product.name}</b></a>
						
						<div>	
							<jsp:directive.include file="item_rating.jsp" />
						</div>
						<%-- <p>${fn:substring(item.product.description, 0, 250)}...</p> --%>
					</div>

					<div
						style="display: inline-block; vertical-align: top; width: 10%; margin-top: 15px;">
						<div>
							<h3>$ ${item.product.price}</h3>
						</div>
						<button class="btnAddCart" id="${item.id}" style="width: 100px;">Add to Cart</button>
					</div>

				</div>
			</c:forEach>

		</c:if>
	</div>
	</main>
	<jsp:include page="footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		
	   $('.btnAddCart').click(function(){
		   itemId = $(this).attr("id");
		   window.location = "add_to_cart?item_id=" + itemId;
	   });
	
	});
	
</script>
</body>
</html>