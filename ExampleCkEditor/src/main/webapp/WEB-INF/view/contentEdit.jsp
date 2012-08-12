<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<title>Content Edit</title>
</head>

<body>

	<h1>Content Edit</h1>

	<form:form modelAttribute="content" method="post">
		<form:hidden path="id" />
		<form:errors path="*"></form:errors>
		
		<div>
			<div><label>Text</label></div>
			<div><form:errors path="text" /></div>
			<div><form:textarea path="text" id="text" rows="10" cols="80" /></div>
		</div>

		<div>
			<input type="submit" />
		</div>
	</form:form>

	<script type="text/javascript" src="../../static/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
	//<![CDATA[
		
		// From the api docs: "This global variable must be set before the editor script loading."
		var CKEDITOR_BASEPATH = "../../static/ckeditor/"
		
		$(document).ready(function() {
			
			$.getScript("../../static/ckeditor/ckeditor.concatenated.js", function(script, textStatus) {
				CKEDITOR.replace( 'text', {
					customConfig : 'ckeditor.config.js'
				});
			});

		});
		
	//]]>
	</script>

</body>
</html>
