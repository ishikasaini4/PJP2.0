<%@ include file="common/header.jspf"%>

<html>
<title>Subtract Dates</title>
<body>
	<h1>Subtract Dates</h1>

	<div class="container">
		<form action="/Subtract" method="get">
			Enter Dates in format (dd-MM-yyyy)<br> <input type="hidden"
				name="operation" value="2"> Date 1: <input type="text"
				name="date1"> <br> Date 2: <input type="text"
				name="date2"> <br>
			<br> <input type="submit" value="Calculate">
		</form>
		<br> <a href="Menu.jsp"> Go Back to Menu </a>
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>