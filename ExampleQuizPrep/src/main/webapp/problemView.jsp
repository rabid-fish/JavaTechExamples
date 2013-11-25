<%@ page import="java.util.*,com.github.rabid_fish.*" %>
<%
	ExamDao categoryDao = new ExamDao();
	Problem[] problems = (Problem[]) session.getAttribute("problems");
	String indexString = request.getParameter("index");
	int index = Integer.parseInt(indexString);
%>
<html>
<head>
	<title>OCJP Exam</title>
	<link href='static/style.css' rel='stylesheet' type='text/css'>
	
	<style type="text/css">
		
		div.inner { padding-top: 35px; }
		div.field { margin-bottom: 10px; }
		div.ordinal { text-align: right; font-size: .8em; font-style: italic; }
		a.returnLink {
			display: block;
			font-size: .8em;
			margin-top: 25px;
		}
		
	</style>
</head>

<body>

<div class="outer">
	<div class="inner">
		<form action="servlet" method="POST">
			<input type="hidden" name="action" value="DISPLAY_QUESTION" />
			<input type="hidden" name="index" value="<%= indexString %>" />
			<div class="ordinal"><%= index + 1 %> of <%= problems.length %></div>
			<div class="field question">Question: <%= problems[index].getQuestion() %></div>
			<div class="field answer">Answer: <input name="guess" type="text" value="" /></div>
			<div class="field"><input type="submit" value="OK" /></div>
		</form>
		
		<div class="bottomNav">
<%
			if (index > 0) {
%>
			<a href="servlet?action=<%=ExamAction.NAVIGATE_QUESTION.toString()%>&amp;index=<%=index - 1%>">Previous Problem</a> |
<%
				}
				if (index < problems.length - 1) {
			%>
			<a href="servlet?action=<%=ExamAction.NAVIGATE_QUESTION.toString()%>&amp;index=<%= index + 1 %>">Next Problem</a> |
<%
			}
%>
			<a href="categoryList.jsp">Return to Categories</a>
		</div>
	</div>
</div>

</body>
</html>
