<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="layoutDefault">
	<tiles:putAttribute name="title" value="Content Edit" />
	<tiles:putAttribute name="script" value="" />
	<tiles:putAttribute name="style" value="" />
	<tiles:putAttribute name="body">

<form:form modelAttribute="content" method="post">
	<form:errors path="*"></form:errors>
	<table>
		<tr>
			<td>Id</td>
			<td><form:input path="id" /></td>
			<td><form:errors path="id" /></td>
		</tr>
		<tr>
			<td>User Id</td>
			<td><form:input path="userId" /></td>
			<td><form:errors path="userId" /></td>
		</tr>
		<tr>
			<td>Created Date</td>
			<td><form:input path="createdDate" /></td>
			<td><form:errors path="createdDate" /></td>
		</tr>
		<tr>
			<td>Text</td>
			<td><form:input path="text" /></td>
			<td><form:errors path="text" /></td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" /></td>
		</tr>
	</table>
</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>
