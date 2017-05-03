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
		<h3>Contact Recommendation Select</h3>
		
		<sf:form modelAttribute="contactRecommendationCommand">
			<c:if test="${not empty flowRequestContext.messageContext.allMessages}">
				<p class="error">There are one or more errors in the form:</p>
				<ul class="error">
					<c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">
						<li>${message.source}: ${message.text}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<div class="fieldGroup" style="margin: 10px 0px;">
				Contacts to select from:
				<ul>
					<c:forEach items="${ contactRecommendationCommand.contacts }" var="contact" varStatus="loop">
						<li><sf:checkbox path="selectedNames" value="${ contact.firstName }"/>${ contact.firstName } ${ contact.lastName }</li>
					</c:forEach>
				</ul>
			</div>
			
			<input name="_eventId_submit" type="submit" value="Submit" />
			<a href="./">Home</a>
		</sf:form>
	</div>
</body>
