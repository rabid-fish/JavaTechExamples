<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue List" />
	<tiles:putAttribute name="body">

<p>${message}</p>

<ul class="queues">
	<li class="queue">
		<span class="queueName">Q1: Example.Queue.In</span>
		<span class="queueMessageCount">85 messages</span>
		<div class="messages">
			<table class="messages">
				<thead>
					<tr>
						<th>Timestamp</th>
						<th>Message ID</th>
						<th>Correlation ID</th>
					</tr>
				</thead>
				<tbody>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="#">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="#">Delete</a>
						</td>
					</tr>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="#">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>view more messages ...</td>
					</tr>
				</tbody>
			</table>
		</div>
	</li>
	<li class="queue">
		<span class="queueName">Q2: Example.Queue.In</span>
		<span class="queueMessageCount">21 messages</span>
		<div class="messages">
			<table class="messages">
				<thead>
					<tr>
						<th>Timestamp</th>
						<th>Message ID</th>
						<th>Correlation ID</th>
					</tr>
				</thead>
				<tbody>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td> 
							<a class="action" href="queue/view">View</a>
							<a class="action" href="#">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="#">Delete</a>
						</td>
					</tr>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="#">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>view more messages ...</td>
					</tr>
				</tbody>
			</table>
		</div>
	</li>
</ul>

	</tiles:putAttribute>
</tiles:insertDefinition>
