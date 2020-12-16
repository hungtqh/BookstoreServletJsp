<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart - HT Store</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />' ></script>
</head>
<body>
<fmt:setLocale value="en_US" scope="session"/>
	<div align="center">
	<jsp:include page="header.jsp"/>
		
		<h3>Your Cart Details</h3>
		<h4 class="message">${message}</h4>	
		
		
		<c:set var="cart" value="${sessionScope['cart']}" />
		
		<c:if test="${cart.totalItems == 0}">
			<h3 class="message">There's no items in your cart</h3>
			
			<div>
			<a style="font-size: 20px;" href='<c:url value = "home" />'>Continue Shopping</a>
		</div>
		
		</c:if>
		
		<c:if test="${cart.totalItems > 0}">
			<div align="center">
			
				<form action="update_cart" method="post" id="cart_form">
				
				<div align="center">
					<table  style="text-align: center;">
						<tr>
							<th>No.</th>
							<th colspan="2">Item</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>SubTotal</th>
							<th></th>
						</tr>
							<c:forEach items="${cart.items}" var="item" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>	
									<td>
										<img src="data:image/jpg;base64, ${item.key.product.base64Image}" class="product-image-admin"> &nbsp;
									</td>	
									<td>
										<span>${item.key.product.name}</span>
									</td> 		
									<td  style="text-align: left;">
										<input type="hidden" name="itemId" value="${item.key.id}">
										<input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" style="text-align: center;">
									</td>						
									<td>
										<fmt:formatNumber type = "currency" value = "${item.key.sellingPrice}"/>
										
									</td>
									<td>
										<fmt:formatNumber type = "currency" value = "${item.value * item.key.sellingPrice}"/>
										
									</td>
									<td>
										<a href="remove_from_cart?item_id=${item.key.id}">Remove</a>
									</td>
									
								</tr>
															
							</c:forEach>
							
						<tr>
							<td colspan="3">
								<button id="btnClearCart" type="button" style="width: 100px;">Clear Cart</button>
							</td>
							<td style="text-align: left;"><b>${cart.totalQuantity} product(s)</b></td>
							<td><b>Total:</b></td>
							<td colspan="2">
								<b><fmt:formatNumber type = "currency" value = "${cart.totalAmount}"/>
								</b>
							</td>
						</tr>	
						
					</table>
					</div>
					
					<div>
						<br/>		
						<a href="${pageContext.request.contextPath}">Continue Shopping</a> &nbsp;&nbsp;
						<a href="#">Checkout</a> &nbsp;&nbsp;
						<button type="submit" style="width: 100px;">Update Cart</button> 
					</div>
				</form>		
			</div>
	
		</c:if>
		
	</div>
	<jsp:include page="footer.jsp"/>
<script type="text/javascript">
	$(document).ready(function(){
		$('#cart_form').validate({
			rules: {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index + 1} : {
						required: true,
						number: true,
						min: 1
					},
				</c:forEach>
			},
			
			messages: {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index + 1} : {
						required: "Please enter quantity",
						number: "Quantity must be a number",
						min: "Quantity must be greater than 0"
					},
				</c:forEach>
			}
		});
		
		$('#btnClearCart').click(function(){
			   window.location = "clear_cart";
		   });
	});
</script>
</body>
</html>