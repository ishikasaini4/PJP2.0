<%@ include file="common/header.jspf"%>

<html>
<title>Subtract Days</title>

<body>
	<h1>Subtract Days from Date</h1>

	<div clas="container">
		<form action="/MinusDays" method="get">
			Enter Date in format (dd-MM-yyyy)<br> <input type="hidden"
				name="operation" value="3"> Date : <input type="text"
				name="date1"> <br> Days : <input type="text"
				name="days"> <br> <br>
			<br> <input type="submit" value="Calculate">
		</form>
		<br> <a href="Menu.jsp"> Go Back to Menu </a>
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>