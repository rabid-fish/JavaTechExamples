<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE>
<html>
<head>
<title><tiles:insertAttribute name="title" /></title>

<style type="text/css">
	<tiles:insertTemplate template="../../static/css/default.css" />
</style>
<style type="text/css">
	<tiles:insertTemplate template="../../static/css/simpleModal.css" />
</style>
<style type="text/css">
	<tiles:insertAttribute name="style" />
</style>
<style type="text/css">
	@import "/ExampleTinyMce/static/css/dataTable.css";
</style>

</head>
<body>

	<div class="content">
		<h3><tiles:insertAttribute name="title" /></h3>
		
		<div class="bodyWrapper">
			<tiles:insertAttribute name="body" />
		</div>
	</div>

	<script type="text/javascript" src="/ExampleTinyMce/static/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="/ExampleTinyMce/static/js/jquery.simpleModal.js"></script>
	<script type="text/javascript" src="/ExampleTinyMce/static/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">

		$(document).ready(function() {
			$('table.dataTable').dataTable();
		} );

		/* Loading page-specific javascript code */
		<tiles:insertAttribute name="script" />
	</script>

</body>
</html>
