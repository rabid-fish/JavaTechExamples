<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="layoutDefault">
	<tiles:putAttribute name="title" value="Content List" />
	<tiles:putAttribute name="style" value="" />
	<tiles:putAttribute name="script">

		$(document).ready(function() {
			$('table.dataTable').dataTable();
		});

	</tiles:putAttribute>
	<tiles:putAttribute name="body">

	<c:if test="${empty list}">
	<p>${message}</p>
	</c:if>
	
	<c:if test="${not empty list}">
		<div class="tableWrapper">
			<table class="display dataTable">
				<thead>
					<tr>
						<th>Id</th>
						<th>Text</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="content">
						<tr>
							<td class="center"><a href="update?id=${content.id}">${content.id}</a></td>
							<td class="center"><a href="update?id=${content.id}"><c:out  value="${content.text}" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>Id</th>
						<th>Text</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</c:if>

	<div class="buttons">
		<a href="create" class="button">Create a new Content Item</a>
	</div>	

	</tiles:putAttribute>
</tiles:insertDefinition>
