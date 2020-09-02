<%@ include file="common/header.jspf"%>

<html>
<title>Subtract Weeks</title>

<body>
	<h1>Subtract Weeks from Date</h1>

	<div class="container">
		<form action="/MinusWeeks" method="get">
			Enter Date in format (dd-MM-yyyy) <br> <input type="hidden"
				name="operation" value="4"> Date : <input type="text"
				name="date1"> <br> Weeks : <input type="text"
				name="weeks"> <br>
			<br> <input class="btn btn-primary" type="submit" value="Calculate">
		</form>
		<br> <a href="Menu.jsp"> Go Back to Menu </a>
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>