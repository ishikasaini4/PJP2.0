<%@ include file="common/header.jspf"%>

<html>
<title>Subtract Months</title>

<body>
	<h1>Subtract Months from Date</h1>
	<div class="container">
		<form action="/MinusMonths" method="get">
			Enter Date in format (dd-MM-yyyy)
			<br>
			<input type="hidden" name="operation" value="5">
			Date : <input type="text" name="date1">
			<br>
			Months : <input type="text" name="months">
			<br><br>
			<input class="btn btn-primary" type="submit" value="Calculate">
		</form>
		<br>
		<a href="Menu.jsp"> Go Back to Menu </a>
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>