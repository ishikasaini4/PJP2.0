<html>
<title>Subtract Weeks</title>

<body>
	<h1>Subtract Weeks from Date</h1>
	
	<form action="OperationsServlet" method="get">
		Enter Date in format (dd-MM-yyyy)
		<br>
		<input type="hidden" name="operation" value="4">
		
		Date : <input type="text" name="date1">
		<br>
		Weeks : <input type="text" name="weeks">
		<br><br>
		<input type="submit" value="Calculate">
	</form>
	<br>
	<a href="Menu.jsp"> Go Back to Menu </a>
</body>
</html>