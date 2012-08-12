<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>Content List</title>
</head>

<body>

	<h1>Content List</h1>

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
							<td class="center"><a href="update?id=${content.id}">${content.text}</a></td>
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
	
	<a href="create" class="button">Create a new Entry</a>

</body>
</html>
