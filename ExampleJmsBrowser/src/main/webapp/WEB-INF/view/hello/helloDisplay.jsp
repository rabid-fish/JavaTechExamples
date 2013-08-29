<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="body">

<p>${message}</p>
<p id="updateTime">The server-side time is: <span id="theTime"></span></p>
<p>Click <a href="hello/ajax">here</a> to view an AJAX version of this page</p>

	</tiles:putAttribute>
</tiles:insertDefinition>
