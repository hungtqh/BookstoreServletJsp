<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>HT Book Store Administration</title>
	<link rel="stylesheet" href='<c:url value = "/css/style.css" />'>
	<script type="text/javascript" src='<c:url value = "/js/jquery-3.4.1.min.js" />'></script>
	<script type="text/javascript" src='<c:url value = "/js/jquery.validate.min.js" />'></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<main>
		
		<div align="center">
			<h3>${message}</h3>
		</div>
		
		<br/><br/><br/><br/><br/><br/><br/><br/>
		<hr width=50%>
	</main>
	<jsp:include page="footer.jsp"/>
</body>
</html>