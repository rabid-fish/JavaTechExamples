package com.github.rabid_fish.service;

import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.model.QueueData;

public interface QueueService {

	Iterable<MessageData> getMessageDataIterable(String queueName);
	Iterable<QueueData> getQueueDataIterable();
	
}
