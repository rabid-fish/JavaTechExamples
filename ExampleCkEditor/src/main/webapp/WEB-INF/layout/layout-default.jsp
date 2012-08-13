<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE>
<html>
<head>
	<title><tiles:insertAttribute name="title" /></title>
	<style type="text/css">
		@import "../../static/css/default.css";
		@import "../../static/css/dataTable.css";
		<tiles:insertAttribute name="style" />
	</style>
</head>

<body>

	<div class="content">
		<h1><tiles:insertAttribute name="title" /></h1>

		<div class="bodyWrapper">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	
	<script type="text/javascript" src="../../static/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="../../static/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
	//<![CDATA[
		
	<tiles:insertAttribute name="script" />

	//]]>
	</script>

</body>
</html>
