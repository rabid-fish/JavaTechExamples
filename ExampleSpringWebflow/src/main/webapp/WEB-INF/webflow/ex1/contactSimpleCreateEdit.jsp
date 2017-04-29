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
		<h3>Create / Edit Contact</h3>
		
		<sf:form modelAttribute="currentContactSimple">
			<c:if test="${not empty flowRequestContext.messageContext.allMessages}">
				<p class="error">There are one or more errors in the form:</p>
				<ul class="error">
					<c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">
						<li>${message.source}: ${message.text}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<div class="fieldGroup">
				<sf:label path="firstName">First Name:</sf:label>
				<sf:errors path="firstName" cssClass="error" />
				<span class="field"><sf:input path="firstName" /></span>
			</div>
			<div class="fieldGroup">
				<sf:label path="lastName">Last Name:</sf:label>
				<sf:errors path="lastName" cssClass="error" />
				<span class="field"><sf:input path="lastName" /></span>
			</div>
			<div class="fieldGroup">
				<sf:label path="phone">Phone:</sf:label>
				<sf:errors path="phone" cssClass="error" />
				<span class="field"><sf:input path="phone" /></span>
			</div>
			<input name="_eventId_submit" type="submit" value="Submit" />
		</sf:form>
	</div>
</body>
