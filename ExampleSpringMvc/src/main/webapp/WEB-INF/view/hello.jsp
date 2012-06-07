<!DOCTYPE>
<html>
<head>
<title>Welcome</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		setInterval("updateTime()", 1000);
		updateTime();
	});
	
	function updateTime() {
		
		$.getJSON("hello/getTime", function(data) {
			$("#theTime").html(data.theTime);
		});
	};

</script>
</head>

<body>

	<h1>Welcome</h1>
	<p>${message}</p>
	<p id="updateTime">The server-side time is: <span id="theTime"></span></p>

</body>
</html>