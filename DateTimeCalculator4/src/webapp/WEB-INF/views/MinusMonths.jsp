<html>
<title>Subtract Months</title>

<body>
	<h1>Subtract Months from Date</h1>
	
	<form action="OperationsServlet" method="get">
		Enter Date in format (dd-MM-yyyy)
		<br>
		<input type="hidden" name="operation" value="5">
		Date : <input type="text" name="date1">
		<br>
		Months : <input type="text" name="months">
		<br><br>
		<input type="submit" value="Calculate">
	</form>
	<br>
	<a href="Menu.jsp"> Go Back to Menu </a>
</body>
</html>