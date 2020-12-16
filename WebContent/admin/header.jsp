<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div align="center">
		<a href='<c:url value = "/admin/home" />'><img style="width: 300px;" alt="logo" src='<c:url value = "/images/logo.png" />' ></a>
		
		<div style="margin-bottom: 20px">
			Welcome, <c:out value="${sessionScope.username}" /> | <a href="logout">Logout</a>
		</div>		
		<div class="header-menu">
			<div>
				<a href="list_users">
					<img src='<c:url value = "/images/users.png" />' > <br/>Accounts
				</a> 
			</div>
			<div>
				<a href="list_category">
					<img src='<c:url value = "/images/category.png" />' > <br/>Categories
				</a>
			</div>	
			<div>
				<a href="list_products">
					<img style="width: 66px; height: 66px;" src='<c:url value = "/images/product.png" />' > <br/>Products
				</a> 
			</div>
			<div>
				<a href="list_item">
					<img style="width: 66px; height: 66px;" src='<c:url value = "/images/item.png" />' > <br/>Items
				</a> 
			</div>
			<div>
				<a href="list_customer">
					<img src='<c:url value = "/images/customer.png" />' > <br/>Customers
				</a>
			</div>
			<div>
				<a href="list_review">
					<img src='<c:url value = "/images/review.png" />' > <br/>Reviews
				</a> 
			</div>	
			<div>
				<a href="list_order">
					<img src='<c:url value = "/images/order.png" />' > <br/>Orders
				</a>
			</div>					
		</div>
	</div>
</header>