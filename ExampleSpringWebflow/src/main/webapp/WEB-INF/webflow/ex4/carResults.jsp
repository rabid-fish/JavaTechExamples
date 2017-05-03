<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<title>Example Spring Webflow</title>
	<link rel="stylesheet" type="text/css" href="/ExampleSpringWebflow/static/common.css" />
</head>
<body>
	<div>
		<h3>Car Results</h3>
		
		<sf:form>
			<div class="fieldGroup" style="margin: 10px 0px;">
				Selections that were made:
				
				<c:forEach items="${ carSelectorCommand.mapContactIdToCarIds }" var="mapItem">
					<p>${ mapItem.key }: ${ mapItem.value }</p>
				</c:forEach>
			</div>
			
			<input name="_eventId_submit" type="submit" value="Done" />
			<a href="./">Home</a>
		</sf:form>
	</div>
</body>
