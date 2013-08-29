<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue List" />
	<tiles:putAttribute name="body">

<p>${message}</p>
<ul>
	<li>
		<span>Q1: Example.Queue.In</span>
		<span>85 messages</span>
	</li>
	<li>
		<span>Q2: Example.Queue.In</span>
		<span>21 messages</span>
	</li>
</ul>

	</tiles:putAttribute>
</tiles:insertDefinition>
