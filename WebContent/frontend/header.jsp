<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div align="center">
		<div>
			<a href='<c:url value = "home" />'> <img alt="logo" style="width: 300px;"
				src='<c:url value = "/images/logo.png" />'></a>
		</div>

		<div>
			<form action="search" method="get" id="search_box">
				<input type="text" name="keyword"> 
				<input type="submit" value="Search"> &nbsp;&nbsp;&nbsp;&nbsp; 
					
					<c:if test="${loggedCustomer == null}">
						<a href="login">Sign In</a> &nbsp; | &nbsp; 
						<a href="register">Register</a> &nbsp; | &nbsp; 
					</c:if>
					
					<c:if test="${loggedCustomer != null}">
						Welcome, <a href="view_profile">${loggedCustomer.person.fullName.firstName}</a> &nbsp; | &nbsp; 
						<a href="logout">Logout</a>&nbsp; &nbsp; | &nbsp; 
						<a href="view_address">My Address</a> &nbsp; | &nbsp;
						<a href="view_order">My Order</a> &nbsp; | &nbsp; 
					</c:if>
					
					<a href="view_cart">Cart</a>
			</form>
		</div>
		<div>&nbsp;</div>
		<div>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<a href="view_category?id=${category.id}"> <font
					size="+1"><b><c:out value="${category.name}" /></b></font>
				</a>
				<c:if test="${not status.last}">
					&nbsp; | &nbsp;
				</c:if>
			</c:forEach>
		</div>

	</div>
</header>