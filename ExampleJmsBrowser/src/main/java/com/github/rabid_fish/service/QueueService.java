package com.github.rabid_fish.service;

import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.model.MessageData;

public interface QueueService {

	Iterable<ConfigQueue> getQueueConfigIterable();
	Iterable<MessageData> getMessageDataIterable(String queueName);
	
}
