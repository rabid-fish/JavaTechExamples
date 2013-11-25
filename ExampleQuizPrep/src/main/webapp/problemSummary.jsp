<%@ page import="java.util.*,com.github.rabid_fish.*" %>
<%
	ExamDao categoryDao = new ExamDao();
	Problem[] problems = (Problem[]) session.getAttribute("problems");
	String[] guesses = (String[]) session.getAttribute("guesses");
	boolean[] corrects = (boolean[]) session.getAttribute("corrects");
	int correctTotal = (Integer) session.getAttribute("correctTotal");
%>
<html>
<head>
	<title>OCJP Exam</title>
	<link href='static/style.css' rel='stylesheet' type='text/css'>
	
	<style type="text/css">
		
		ul {
			list-style-type: none;
			padding: 0px;
			margin: 0px;
		}
		
		li {
			margin: 15px;
		}
		
		p.totalCorrect {
			font-weight: bold;
		}
		
	</style>
</head>

<body>

<div class="outer">
	<div class="inner">
		<h3>Summary</h3>
		<p class="totalCorrect">Total correct: <%= correctTotal %> of <%= problems.length %></p>
		<ul>
<%
			for (int i = 0; i < problems.length; i++) {
				String color = corrects[i] ? "green" : "red";
%>
			<li>
				<div class="question">Question: <%= problems[i].getQuestion() %></div>
				<div class="answer">Answer: <%= problems[i].getAnswer() %></div>
				<div class="guess" style="color: <%= color %>">Guess: <%= guesses[i] %></div>
			</li>
<%
			}
%>
		</ul>
		<a href="categoryList.jsp">Return to Categories</a>
	</div>
</div>

</body>
</html>
