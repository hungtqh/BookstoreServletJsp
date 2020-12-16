<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${item.product.name} - HT Store</title>
<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
	<div class="center table-detail">
		<table class="product-in-detail">
			<tr class="table-padding">
				<td class="table-align" colspan="3">
					<h2 class="title">${item.product.name}</h2> 
				<td>
			</tr>

			<tr>
				<td rowspan="2"><img
					src="data:image/jpg;base64, ${item.product.base64Image}"
					class="product-img-detail"></td>
				<td class="rating"><b>Rating:</b> <jsp:include page="item_rating.jsp" /> &nbsp; &nbsp; 
				
				<a href="#reviews">${fn:length(item.reviews)} Reviews</a></td>
				
				<td valign="top" rowspan="2"><h2>$ ${item.sellingPrice}</h2> <br />
					<button id="btnAddCart" type="submit" style="width: 90px;">Add to Cart</button>
				</td>
			</tr>

			<tr>
				<td valign="top" style="padding-right: 10px; text-align: justify;">
					<br/><br/>
					${item.product.description}
				</td>
			</tr>

			<tr>
				<td id="reviews"><h3>Customer Reviews</h3></td>
				<td align="center">
					<button id="btn_write_review" style="width: 180px;">Write a Customer Review</button>
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					<table style="margin-left: 0;">
						<c:forEach items="${item.reviews}" var="review">
							<tr>
								<td>
									<c:forTokens items="${review.stars}" delims="," var="star">
										<c:if test="${star eq 'on'}">
											<img src='<c:url value = "/images/stars/rating_on.png" />'>
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src='<c:url value = "/images/stars/rating_off.png" />'>
										</c:if>
								</c:forTokens>
								<b>${review.headline}</b>
								</td>								
							</tr>
							
							<tr>
								<td>
									by ${review.customer.person.fullName.firstName} ${review.customer.person.fullName.lastName} on <fmt:formatDate pattern='dd/MM/yyyy' value='${review.reviewTime}' />  
								</td>
							</tr>
							
							<tr>
								<td><i>${review.comment}</i></td> 
							</tr>
							
							<tr>
								<td><hr size="1px" width="200px"></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			
		</table>
	</div>
	</main>
	<jsp:include page="footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		
	   $('#btn_write_review').click(function(){
		   window.location = "write_review?item_id=" + ${item.id};
	   });
		
	   $('#btnAddCart').click(function(){
		   window.location = "add_to_cart?item_id=" + ${item.id};
	   });
	});
</script>	
</body>
</html>