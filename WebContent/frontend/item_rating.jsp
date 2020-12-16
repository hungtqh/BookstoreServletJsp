<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:forTokens items="${item.ratingStars}" delims="," var="star">
		<c:if test="${star eq 'on'}">
			<img src='<c:url value = "/images/stars/rating_on.png" />'>
		</c:if>
		<c:if test="${star eq 'off'}">
			<img src='<c:url value = "/images/stars/rating_off.png" />'>
		</c:if>
		<c:if test="${star eq 'half'}">
			<img src='<c:url value = "/images/stars/rating_half.png" />'>
		</c:if>
	</c:forTokens>
