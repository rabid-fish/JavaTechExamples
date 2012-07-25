<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="layoutDefault">
	<tiles:putAttribute name="title" value="Content List" />
	<tiles:putAttribute name="script" value="" />
	<tiles:putAttribute name="style" value="" />
	<tiles:putAttribute name="body">

<h3>Content</h3>

<c:if test="${empty list}">
<p>${message}</p>
</c:if>

<c:if test="${not empty list}">
	<div class="tableWrapper">
		<table class="display dataTable">
			<thead>
				<tr>
					<th>Id</th>
					<th>User Id</th>
					<th>Created Date</th>
					<th>Text</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="content">
					<tr>
						<td class="center"><a href="update?id=${content.id}">${content.id}</a></td>
						<td class="center"><a href="update?id=${content.id}">${content.userId}</a></td>
						<td class="center"><a href="update?id=${content.id}">${content.createdDate}</a></td>
						<td class="center"><a href="update?id=${content.id}">${content.text}</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>Id</th>
					<th>User Id</th>
					<th>Created Date</th>
					<th>Text</th>
				</tr>
			</tfoot>
		</table>
	</div>
</c:if>

<a href="create" class="button">Create new Content</a>

	</tiles:putAttribute>
</tiles:insertDefinition>
