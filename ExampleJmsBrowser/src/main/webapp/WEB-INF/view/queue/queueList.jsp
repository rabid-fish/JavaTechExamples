<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue List" />
	<tiles:putAttribute name="body">

<c:if test="${empty list}">
<p>${message}</p>
</c:if>

<c:if test="${not empty list}">
<ul class="queues">
	<c:forEach items="${list}" var="queueData">
	<li class="queue">
		<div class="queueSearch">
			<span class="queueSearchLabel">search queue:</span>
			<input class="queueSearchInput" type="text" name="" value="" />
		</div>
		<span class="queueName">Queue: ${queueData.queueConfig.name}</span>
		<span class="queueMessageCount">${queueData.jmsQueueStats.queueSize} messages</span>
		<div class="messages">
			<table class="messages">
				<thead>
					<tr>
						<c:forEach items="${queueData.queueConfig.columns}" var="column">
						<th>${column.title}</th>
						</c:forEach>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${queueData.messageDataList}" var="message">
					<tr class="message stripe">
						<c:forEach items="${message.dataValueList}" var="messageDataValue">
						<td>${messageDataValue}</td>
						</c:forEach>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
<!-- 
				<tbody>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>view more messages ...</td>
					</tr>
				</tbody>
 -->
			</table>
		</div>
	</li>
	</c:forEach>
<!-- 
	<li class="queue">
		<div class="queueSearch">
			<span class="queueSearchLabel">search queue:</span>
			<input class="queueSearchInput" type="text" name="" value="" />
		</div>
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
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					<tr class="message stripe">
						<td>2013-08-09 11:14:17:687 CDT </td>
						<td>ID:DESBAPP01-58182-1376050924672-1:1:3:1:1</td>
						<td>388e3529-21b9-49ef-94f6-4a1518fe6b66</td>
						<td>
							<a class="action" href="queue/view">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					<tr class="message">
						<td>view more messages ...</td>
					</tr>
				</tbody>
			</table>
		</div>
	</li>
 -->
</ul>
</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>
