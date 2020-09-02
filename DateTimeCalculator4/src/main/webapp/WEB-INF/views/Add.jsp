<%@ include file="common/header.jspf"%>

<html>
<title>Add Dates</title>
<body>
	<h1>Add Dates</h1>

	<div class="container">
		<form action="/Add" method="get">
			Enter Dates in format (dd-MM-yyyy)<br> <input type="hidden"
				name="operation" value="1"> Date 1: <input type="text"
				name="date1"> <br> Date 2: <input type="text"
				name="date2"> <br> <br> <input type="submit"
				value="Calculate">
		</form>
		<br> <a href="Menu.jsp"> Go Back to Menu </a>
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>