<%@ include file="common/header.jspf"%>
<html>
<title>Day of Week</title>

<body>
	<h1>Get Day of Week</h1>
	
	<form action="OperationsServlet" method="get">
		Enter Date in format (dd-MM-yyyy)<br>
		<input type="hidden" name="operation" value="6">
		Date : <input type="text" name="date1">
		<br>
		<br>
		<input class="btn btn-primary" type="submit" value="Calculate">
	</form>
		<br>
	<a href="Menu.jsp"> Go Back to Menu </a>
</body>
</html>
<%@ include file="common/footer.jspf"%>