<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue List" />
	<tiles:putAttribute name="body">

<c:if test="${not empty list}">
<ul class="queues">
	<c:forEach items="${list}" var="queueData">
	<li class="queue">
		<span class="queueName">Queue: ${queueData.queueConfig.name}</span>
		<span class="queueMessageCount">${queueData.jmsQueueStats.queueSize} messages</span>
		<div class="queueSearch">
			<span class="queueSearchLabel">search queue:</span>
			<input class="queueSearchInput" type="text" name="" value="" />
			<a class="queueSearchButton" href="#">go</a>
		</div>
		<div class="messages">
			<c:if test="${empty queueData.messageDataList}">
			<p class="messagesEmpty">There are no messages currently in this queue</p>
			</c:if>
			
			<c:if test="${not empty queueData.messageDataList}">
			<table class="messages">
				<thead>
					<tr>
						<c:forEach items="${queueData.queueConfig.columns}" var="column">
						<th>${fn:escapeXml(column.title)}</th>
						</c:forEach>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${queueData.messageDataList}" var="message" varStatus="stripe">
					<tr class="message ${stripe.index % 2 == 0 ? 'stripe' : ''}">
						<c:forEach items="${message.dataValueList}" var="messageDataValue">
						<td>${fn:escapeXml(messageDataValue)}</td>
						</c:forEach>
						<td>
							<a class="action" href="queue/view?messageId=${fn:escapeXml(message.messageId)}&amp;queueName=${fn:escapeXml(queueData.queueConfig.name)}">View</a>
							<a class="action" href="queue/delete">Delete</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${queueData.jmsQueueStats.queueSize > queueData.queueConfig.maxMessageCount}">
			<div class="ellipseDiv">
				<a class="ellipseButton" href="#">more results ...</a>
			</div>
			</c:if>
			</c:if>
		</div>
	</li>
	</c:forEach>
</ul>
</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>
