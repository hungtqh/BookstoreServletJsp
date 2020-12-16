<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Not Found Error</title>
</head>
<body>
<div align="center">
	<header>
		<div>
			<img style="width: 300px;" alt="logo" src='<c:url value = "/images/logo.png" />'>
		</div>
	</header>
	
	<main>
		<div>
			<h2>Sorry, the requested page could not be found.</h2>
		</div>
		<div>
			<a href="javascript:history.go(-1)">Go Back</a>
		</div>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	</main>

	<footer>
		<c:set var="year"
			value="<%=Calendar.getInstance().get(Calendar.YEAR)%>" />
		<div align="center">
			<h4>
				Copyright &copy;
				<c:out value="${year}" />
				by HT Store
			</h4>
		</div>
	</footer>
</div>
</body>
</html>