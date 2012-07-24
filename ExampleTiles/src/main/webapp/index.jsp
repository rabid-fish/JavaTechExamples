<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<p>Start main page</p>

<tiles:insertDefinition name="layoutNoChrome">
	<tiles:putAttribute name="body">
		
		<p>Hello from Tiles!</p>
		
	</tiles:putAttribute>
</tiles:insertDefinition>

<p>End main page</p>
