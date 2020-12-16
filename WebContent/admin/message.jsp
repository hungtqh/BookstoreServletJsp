<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HT Book Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />' >
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h3>${message}</h3>
		</div>
		<hr width=50%>
		
		
	</main>
	<jsp:include page="footer.jsp"/>
</body>
</html>