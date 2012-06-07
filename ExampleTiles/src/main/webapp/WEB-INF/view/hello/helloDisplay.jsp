<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layoutWithTemplateExpressionAdvanced">
	<tiles:putAttribute name="body">

<p>${message}</p>
<p id="updateTime">The server-side time is: <span id="theTime"></span></p>
	
	</tiles:putAttribute>
</tiles:insertDefinition>
