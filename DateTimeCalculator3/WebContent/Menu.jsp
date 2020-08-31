<html>
<title>Date Time Calculator</title>
<body>
	<h1 align="center">Date Time Calculator</h1>
	<h2 align="center">MENU</h2>

	<form action="CalculatorServlet" method="get">
		<input type="radio" name="choice" value="1"> Add Dates
		<br>
		<input type="radio" name="choice" value="2"> Subtract Dates	
		<br>
		<input type="radio" name="choice" value="3"> Subtract Days from Date	
		<br>
		<br>
		<input type="radio" name="choice" value="4"> Subtract Weeks from Date
		<br>
		<input type="radio" name="choice" value="5"> Subtract Months from Date
		<br>
		<input type="radio" name="choice" value="6"> Day of the Week 
		<br>
		<input type="radio" name="choice" value="7"> Week Number of the Year
		<br>
		<input type="radio" name="choice" value="8"> Natural Language Phrase to Date
		<br><br>
		<input type="submit" value="GO!">
	</form>
</body>
</html>