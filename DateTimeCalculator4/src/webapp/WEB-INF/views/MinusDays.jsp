<html>
<title>Subtract Days</title>

<body>
	<h1>Subtract Days from Date</h1>
	
	<form action="OperationsServlet" method="get">
		Enter Date in format (dd-MM-yyyy)<br>
		<input type="hidden" name="operation" value="3">
		
		Date : <input type="text" name="date1">
		<br>
		Days : <input type="text" name="days">
		<br>
		<br><br>
		<input type="submit" value="Calculate">
	</form>
		<br>
	<a href="Menu.jsp"> Go Back to Menu </a>
</body>
</html>