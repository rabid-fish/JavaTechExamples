<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="layoutDefault">
	<tiles:putAttribute name="title" value="Content Edit" />
	<tiles:putAttribute name="style" value="" />
	<tiles:putAttribute name="script">
	
		// From the api docs: "This global variable must be set before the editor script loading."
		var CKEDITOR_BASEPATH = "../../static/ckeditor/"
		
		$.getScript("../../static/ckeditor/ckeditor.concatenated.js", function(script, textStatus) {
			CKEDITOR.replace( 'text', {
				customConfig : 'ckeditor.config.js'
			});
		});
		
	</tiles:putAttribute>
	<tiles:putAttribute name="body">

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

	<div class="buttons">
		<a href="list" class="button">Return to the List</a>
	</div>
	
	</tiles:putAttribute>
</tiles:insertDefinition>
