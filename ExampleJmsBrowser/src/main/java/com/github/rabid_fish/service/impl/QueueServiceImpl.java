package com.github.rabid_fish.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabid_fish.config.QueueConfigList;
import com.github.rabid_fish.config.QueueConfigViewHelper;
import com.github.rabid_fish.config.QueueConfigListHelper;
import com.github.rabid_fish.jms.ActiveMqJmxBrowser;
import com.github.rabid_fish.jms.JmsBrowser;
import com.github.rabid_fish.jms.JmsQueueStats;
import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.model.QueueData;
import com.github.rabid_fish.service.QueueService;

@Service
public class QueueServiceImpl implements QueueService {

	@Autowired
	private JmsBrowser jmsBrowser;
	
	@Autowired
	private ActiveMqJmxBrowser jmxBrowser;

	@Autowired
	private QueueConfigListHelper configListHelper;
	
	@Autowired
	private QueueConfigViewHelper configDetailViewHelper;
	
	@Override
	public Iterable<MessageData> getMessageDataIterable(String queueName) {
		
		QueueConfigList queueConfigListForQueueName = configListHelper.getQueueConfigForQueueName(queueName);
		if (queueConfigListForQueueName == null) {
			throw new RuntimeException("Queue config not found for queue '" + queueName + "'");
		}
		
		return jmsBrowser.browseTopMessages(queueConfigListForQueueName);
	}
	
	@Override
	public Iterable<QueueData> getQueueDataIterable() {
		
		List<QueueData> queueDataList = new ArrayList<QueueData>();
		QueueConfigList[] queueConfigListArray = configListHelper.getQueueConfigListArray();
		
		for(QueueConfigList queueConfigList : queueConfigListArray) {
			QueueData queueData = getQueueDataForQueueName(queueConfigList);
			queueDataList.add(queueData);
		}
		
		return queueDataList;
	}

	QueueData getQueueDataForQueueName(QueueConfigList queueConfigList) {
		
		QueueData queueData = new QueueData();
		queueData.setQueueConfigList(queueConfigList);
		
		String queueName = queueConfigList.getName();
		JmsQueueStats queueStats = jmxBrowser.getQueueStats(queueName);
		queueData.setJmsQueueStats(queueStats);
		
		Iterable<MessageData> messageDataList = getMessageDataIterable(queueName);
		queueData.setMessageDataList(messageDataList);
		
		return queueData;
	}

	@Override
	public MessageData getDetailedMessageDataForMessageId(String queueName, String messageId) {

		MessageData messageData = jmsBrowser.browseMessageInDetail(
				configDetailViewHelper.getQueueConfigDetailView(), queueName, messageId);
		
		return messageData;
	}

	@Override
	public void removeMessage(String queueName, String messageId) {
		
		jmsBrowser.deleteMessage(queueName, messageId);
	}

}
