<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="body">

<p>${message}</p>
<p id="updateTime">The server-side time is: <span id="theTime"></span></p>
<p>Click <a href="hello/ajax">here</a> to view a chromeless version of this page, suitable for embedding in sections of pages</p>

	</tiles:putAttribute>
</tiles:insertDefinition>
