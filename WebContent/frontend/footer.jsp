<%@page import="java.util.Calendar"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
	<footer> 
		<c:set var = "year" value = "<%= Calendar.getInstance().get(Calendar.YEAR) %>" />
		<div align="center">
			<h4>Copyright &copy; <c:out value="${year}" /> by HT Store</h4>
			<a href="#">About Us</a> &nbsp;
			<a href="#">Contact Us</a> &nbsp;
			<a href="#">Privacy Policy</a> &nbsp;
			<a href="#">Shipping &amp; Delivery</a>
		</div>
	</footer>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#search_box').validate({
			rules: {
				keyword: "required"
			},
			
			messages: {
				keyword: ""
			}
		});
	});
</script>