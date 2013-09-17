package com.github.rabid_fish.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabid_fish.config.QueueConfigHelper;
import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.jms.JmsBrowser;
import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.service.QueueService;

@Service
public class QueueServiceImpl implements QueueService {

	@Autowired
	JmsBrowser browser;
	
	@Autowired
	private QueueConfigHelper configHelper;

	@Override
	public Iterable<QueueConfig> getQueueConfigIterable() {
		return Arrays.asList(configHelper.getConfigQueueArray());
	}

	@Override
	public Iterable<MessageData> getMessageDataIterable(String queueName) {
		
		QueueConfig configQueueForQueueName = configHelper.getConfigQueueForQueueName(queueName);
		if (configQueueForQueueName == null) {
			throw new RuntimeException("Queue config not found for queue '" + queueName + "'");
		}
		
		return browser.browseTopMessages(configQueueForQueueName);
	}

}
