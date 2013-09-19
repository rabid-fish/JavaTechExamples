<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:insertDefinition name="layout">
	<tiles:putAttribute name="title" value="Queue View" />
	<tiles:putAttribute name="body">

<style type="text/css">

	div.messages {
		width: 900px;
	}

	table.messageData {
		font-size: .8em;
		border: 1px solid black;
	}
	
	table.messageData th {
		color: #999999;
		font-size: .7em;
		padding: 0px 5px;
	}
	
	table.messageData td {
		padding: 2px 5px;
	}
	
	td.messageDataTitle {
		width: 14%;
	}
	
	td.messageDataValue {
		width: 31%;
	}
	
	label {
		font-weight: bold;
		font-size: .8em;
	}

	div.messageBodyText {
		font-family: monospace;
		white-space: pre;
		border: 1px solid black;
		padding: 10px;
	}
	
	div.backButton {
		padding: 10px;
	}
	
</style>

<ul class="queues">
	<li class="queue">
		<div class="queueTitle">
			<span class="queueName">${queueName}</span>
			<!-- 
			<div class="actions">
				<ul class="actions">
					<li class="action"><a class="action" href="#">Copy</a></li>
					<li class="action"><a class="action" href="#">Delete</a></li>
				</ul>
			</div>
			 -->
		</div>
		<div class="messages">
			<div class="messageBody">
				<label class="messageBodyLabel">Message Body</label>
				<div class="messageBodyText">${fn:escapeXml(message.body)}</div>
			</div>

			<div class="messageData">
				<label class="messageDataLabel">Message Properties</label>
				<table class="messageData">
					<thead>
						<tr>
							<th>key</th>
							<th>value</th>
							<th class="spacer"></th>
							<th>key</th>
							<th>value</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="0" end="${fn:length(message.dataTitleList) - 1}" step="2" varStatus="loop">
						<tr class="message ${loop.index % 4 == 0 ? 'stripe' : ''}">
							<c:if test="${not empty message.dataTitleList[loop.index]}">
							<td class="messageDataTitle">${message.dataTitleList[loop.index]}</td>
							<td class="messageDataValue">${message.dataValueList[loop.index]}</td>
							</c:if>
							<td class="spacer"></td>
							<c:if test="${not empty message.dataTitleList[loop.index + 1]}">
							<td class="messageDataTitle">${message.dataTitleList[loop.index + 1]}</td>
							<td class="messageDataValue">${message.dataValueList[loop.index + 1]}</td>
							</c:if>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="backButton">
			<a class="button backButton" href="../../">Return to Queue List</a>
		</div>
	</li>
</ul>

	</tiles:putAttribute>
</tiles:insertDefinition>
