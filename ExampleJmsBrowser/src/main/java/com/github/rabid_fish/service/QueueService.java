package com.github.rabid_fish.service;

import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.model.MessageData;

public interface QueueService {

	Iterable<QueueConfig> getQueueConfigIterable();
	Iterable<MessageData> getMessageDataIterable(String queueName);
	
}
