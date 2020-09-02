<%@ include file="common/header.jspf"%>

<html>
<title>NLP to Date</title>

<body>
	<h1>Natural Language Phrase to Date</h1>
	<div class="container">
		<form action="/NLPToDate" method="get" >
			<input type="hidden" name="operation" value="8">
			Phrase : <input type="text" name="phrase">
			<br><br>
			<input type="submit" value="Calculate">
		</form>
			<br>
		<a href="Menu.jsp"> Go Back to Menu </a>
	</div>
</body>
</html>
<%@ include file="common/footer.jspf"%>