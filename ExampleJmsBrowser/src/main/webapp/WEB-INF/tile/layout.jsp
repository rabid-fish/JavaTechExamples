<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE>
<html>
<head>
	<title>ExampleJmsBrowser - <tiles:insertAttribute name="title" /></title>
	<script type="text/javascript" src="/ExampleJmsBrowser/static/js/jquery-2.0.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/ExampleJmsBrowser/static/css/theme.css">
</head>

<body>

	<div id="container">
		<div id="header">
			<div id="header_inner">
				<div id="banner">ExampleJmsBrowser</div>
				<div id="nav">
					<ul>
						<li class="active">Home</li>
						<li class="inactive">Other</li>
					</ul>
				</div>
				<div id="search"></div>
			</div>
		</div>
		<div id="content">
			<div id="content_inner">
				<h1><tiles:insertAttribute name="title" /></h1>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div id="footer">
			<div id="footer_inner">
				<span>Demo made possible thanks to the existence of:</span>
				<ul>
					<li><a href="https://github.com">Github</a></li>
					<li><a href="http://activemq.apache.org/">ActiveMQ</a></li>
					<li><a href="http://www.springsource.org">Spring</a></li>
					<li><a href="http://tiles.apache.org">Tiles</a></li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>
