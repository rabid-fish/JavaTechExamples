package com.github.rabid_fish.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabid_fish.config.QueueConfigList;
import com.github.rabid_fish.config.QueueConfigSearch;
import com.github.rabid_fish.config.helper.QueueConfigListHelper;
import com.github.rabid_fish.config.helper.QueueConfigSearchHelper;
import com.github.rabid_fish.config.helper.QueueConfigViewHelper;
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
	private QueueConfigViewHelper configViewHelper;
	
	@Autowired
	private QueueConfigSearchHelper configSearchHelper;
	
	@Override
	public Iterable<MessageData> getMessageDataIterable(String queueName) {
		
		QueueConfigList queueConfigForQueueName = configListHelper.getQueueConfigForQueueName(queueName);
		List<MessageData> list = jmsBrowser.browseMessages(queueConfigForQueueName, queueName);
		return list;
	}
	
	@Override
	public Iterable<MessageData> getMessageDataIterable(String queueName, String search) {
		
		QueueConfigSearchHelper configSearchHelperClone = configSearchHelper.clone(search);
		QueueConfigSearch queueConfigSearch = configSearchHelperClone.getQueueConfigSearch();
		List<MessageData> list = jmsBrowser.browseMessages(queueConfigSearch, queueName);
		return list;
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
				configViewHelper.getQueueConfigDetailView(), queueName, messageId);
		
		return messageData;
	}

	@Override
	public void removeMessage(String queueName, String messageId) {
		
		jmsBrowser.deleteMessage(queueName, messageId);
	}

}
