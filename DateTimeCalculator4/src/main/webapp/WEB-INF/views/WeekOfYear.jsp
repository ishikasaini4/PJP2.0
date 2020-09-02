<%@ include file="common/header.jspf"%>

<html>
<title>Week of Year</title>

<body>
	<h1>Get Week of the Year</h1>
	<div class="container">
		<form action="OperationsServlet" method="get">
		
			Enter Date in format (dd-MM-yyyy)<br>
			<input type="hidden" name="operation" value="7">
			Date : <input type="text" name="date1">
			<br><br>
			<input class="btn btn-primary" type="submit" value="Calculate">
		</form>
			<br>

		<a href="Menu.jsp"> Go Back to Menu </a>
	
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>