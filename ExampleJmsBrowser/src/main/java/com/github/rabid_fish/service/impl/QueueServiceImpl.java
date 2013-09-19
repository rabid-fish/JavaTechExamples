package com.github.rabid_fish.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.config.QueueConfigDetailViewHelper;
import com.github.rabid_fish.config.QueueConfigHelper;
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
	private QueueConfigHelper configHelper;
	
	@Autowired
	private QueueConfigDetailViewHelper configDetailViewHelper;
	
	@Override
	public Iterable<MessageData> getMessageDataIterable(String queueName) {
		
		QueueConfig queueConfigForQueueName = configHelper.getQueueConfigForQueueName(queueName);
		if (queueConfigForQueueName == null) {
			throw new RuntimeException("Queue config not found for queue '" + queueName + "'");
		}
		
		return jmsBrowser.browseTopMessages(queueConfigForQueueName);
	}
	
	@Override
	public Iterable<QueueData> getQueueDataIterable() {
		
		List<QueueData> queueDataList = new ArrayList<QueueData>();
		QueueConfig[] queueConfigArray = configHelper.getQueueConfigArray();
		
		for(QueueConfig queueConfig : queueConfigArray) {
			QueueData queueData = getQueueDataForQueueName(queueConfig);
			queueDataList.add(queueData);
		}
		
		return queueDataList;
	}

	QueueData getQueueDataForQueueName(QueueConfig queueConfig) {
		
		QueueData queueData = new QueueData();
		queueData.setQueueConfig(queueConfig);
		
		String queueName = queueConfig.getName();
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
