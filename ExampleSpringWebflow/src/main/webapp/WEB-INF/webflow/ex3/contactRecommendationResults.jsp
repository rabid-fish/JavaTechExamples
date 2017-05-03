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
		<h3>Contact Recommendation Results</h3>
		
		<sf:form>
			<div class="fieldGroup" style="margin: 10px 0px;">
				Recommended items:
				<ul>
					<c:forEach items="${ contactRecommendationCommand.recommendedContacts }" var="c" varStatus="loop">
						<li>${ c.firstName } ${ c.lastName }</li>
					</c:forEach>
				</ul>
			</div>
			<div class="fieldGroup" style="margin: 10px 0px;">
				Selected items:
				<ul>
					<c:forEach items="${ contactRecommendationCommand.selectedContacts }" var="c" varStatus="loop">
						<li>${ c.firstName } ${ c.lastName }</li>
					</c:forEach>
				</ul>
			</div>
			
			<input name="_eventId_submit" type="submit" value="Done" />
			<a href="./">Home</a>
		</sf:form>
	</div>
</body>
