<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue View" />
	<tiles:putAttribute name="body">

<p>${message}</p>

<ul class="queues">
	<li class="">
	</li>
	<li class="queue">
		<div class="queueTitle">
			<span class="queueName">Q1: Example.Queue.In</span>
			<div class="actions">
				<ul class="actions">
					<li class="action"><a class="action" href="#">Copy</a></li>
					<li class="action"><a class="action" href="#">Delete</a></li>
				</ul>
			</div>
		</div>
		<div class="messages">
			<table class="messages">
				<thead>
					<tr>
						<th>key</th>
						<th>value</th>
					</tr>
				</thead>
				<tbody>
					<tr class="message stripe">
						<td>Timestamp</td>
						<td>2013-08-09 11:14:17:687 CDT </td>
					</tr>
					<tr>
						<td>Message ID</td>
						<td>DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
					</tr>
					<tr>
						<td>Correlation ID</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
					</tr>
				</tbody>
			</table>
		</div>
	</li>
</ul>

	</tiles:putAttribute>
</tiles:insertDefinition>
