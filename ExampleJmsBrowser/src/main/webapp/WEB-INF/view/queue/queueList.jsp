<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue List" />
	<tiles:putAttribute name="body">

<style type="text/css">

	table.messages {
		font-size: 1.0em;
		border: 1px solid black;
	}
	
	table.messages th {
		color: #999999;
		font-size: .7em;
		padding: 0px 5px;
	}
	
	table.messages td {
		padding: 2px 5px;
	}

</style>

<c:if test="${not empty list}">
<ul class="queues">
	<c:forEach items="${list}" var="queueData">
	<li class="queue">
		<span class="queueName">Queue: ${queueData.queueConfig.name}</span>
		<span class="queueMessageCount">${queueData.jmsQueueStats.queueSize} messages</span>
		
		<div class="queueSearch">
			<span class="queueSearchLabel">search queue:</span>
			<input class="queueSearchInput" type="text" name="" value="" />
			<a class="button queueSearchButton" href="#">go</a>
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
					<c:forEach items="${queueData.messageDataList}" var="message" varStatus="loop">
					<tr class="message ${loop.index % 2 == 0 ? 'stripe' : ''}">
						<c:forEach items="${message.dataValueList}" var="messageDataValue">
						<td>${fn:escapeXml(messageDataValue)}</td>
						</c:forEach>
						<td>
							<a class="button action" href="queue/view?messageId=${fn:escapeXml(message.messageId)}&amp;queueName=${fn:escapeXml(queueData.queueConfig.name)}">View</a>
							<a class="button action" href="queue/delete?messageId=${fn:escapeXml(message.messageId)}&amp;queueName=${fn:escapeXml(queueData.queueConfig.name)}">Delete</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${queueData.jmsQueueStats.queueSize > queueData.queueConfig.maxMessageCount}">
			<div class="ellipseDiv">
				<a class="button ellipseButton" href="#">more results ...</a>
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
