<%@ page import="java.util.*,com.github.rabid_fish.*" %>
<%
	ExamDao categoryDao = new ExamDao();
	List<String> categories = categoryDao.listCategories();
%>
<html>
<head>
	<title>OCJP Exam</title>
	<link href='static/style.css' rel='stylesheet' type='text/css'>
</head>

<body>

<div class="outer">
	<div class="inner">
		<h3>Pick a Category:</h3>
		<ul>
<%
			for (String category : categories) {
%>
			<li><%= category %></li>
<%
			}
%>
		</ul>
	</div>
</div>

</body>
</html>
