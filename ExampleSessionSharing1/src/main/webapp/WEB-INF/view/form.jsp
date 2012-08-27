<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>List of Keys and Attributes</h3>
<c:forEach items="${list}" var="item">
	${item}<br />
</c:forEach>

<form action="/ExampleSessionSharing2/app/session" method="post">
	<div><form:errors path="*" /></div>
	<p>
		<label>Key</label>:
		<input name="key" type="text" value="" />
	</p>
	<p>
		<label>Value</label>:
		<input name="value" type="text" value="" />
	</p>
	<p>
		<input name="submit" type="submit" /> | <a href="/ExampleSessionSharing2/app/session/clear">Clear Map</a>
	</p>
</form>
