<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<title>Example Spring Webflow</title>
	<link rel="stylesheet" type="text/css" href="/ExampleSpringWebflow/static/common.css" />
</head>
<body>
	<div>
		Contact count: <span>${ contacts.size() }</span>
		<table>
			<tr>
				<th>first</th>
				<th>last</th>
				<th>phone</th>
				<th>action</th>
			</tr>
		<c:forEach items="${contacts}" var="contact" varStatus="loop">
			<tr>
				<td>${ contact.firstName }</td>
				<td>${ contact.lastName }</td>
				<td>${ contact.phone }</td>
				<td><a href="${flowExecutionUrl}&_eventId=createEdit&id=${ contact.id }">Edit</a></td>
			</tr>
		</c:forEach>
		</table>
		<a href="${flowExecutionUrl}&_eventId=createEdit">Add Contact</a>
		<a href="./">Home</a>
	</div>
</body>
