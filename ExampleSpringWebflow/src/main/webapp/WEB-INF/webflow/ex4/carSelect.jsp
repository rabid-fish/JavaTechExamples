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
		<h3>Car Selector</h3>
		
		<sf:form modelAttribute="carSelectorCommand">
			<c:if test="${not empty flowRequestContext.messageContext.allMessages}">
				<p class="error">There are one or more errors in the form:</p>
				<ul class="error">
					<c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">
						<li>${message.source}: ${message.text}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<div class="fieldGroup" style="margin: 10px 0px;">
				Please select what cars each contact has:
				<c:forEach items="${ carSelectorCommand.contacts }" var="contact" varStatus="contactLoop">
					<div>
						<p>${ contact.firstName } ${ contact.lastName }</p>
						<c:if test="${ empty carSelectorCommand.cars }">
							No cars available
						</c:if>
						
						<c:if test="${ not empty carSelectorCommand.cars }">
							<!-- 
							<c:forEach items="${ carSelectorCommand.cars }" var="car" varStatus="carLoop">
								<p>${ car.id }: ${ car.make } - ${ car.model }</p>
							</c:forEach>
							 -->
							
							<!-- http://stackoverflow.com/questions/9943386/bind-map-in-spring-mvc/9974851#9974851 -->
							<sf:select path="mapContactIdToCarIds['${ contact.id }']" multiple="multiple">
								<c:forEach items="${ carSelectorCommand.cars }" var="car" varStatus="carLoop">
									<sf:option label="${ car.make } - ${ car.model }" value="${ car.id }" />
								</c:forEach>
							</sf:select>
						</c:if>
					</div>
				</c:forEach>
			</div>
			
			<input name="_eventId_submit" type="submit" value="Submit" />
			<a href="./">Home</a>
		</sf:form>
	</div>
</body>
