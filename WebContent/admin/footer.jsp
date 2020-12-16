<%@page import="java.util.Calendar"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
	<footer>
		<c:set var = "year" value = "<%= Calendar.getInstance().get(Calendar.YEAR) %>" />
		<div align="center">
			<h4>HT Store Administration</h4>
			<h4>Copyright &copy; <c:out value="${year}" /> by HT Store</h4>
		</div>
	</footer>