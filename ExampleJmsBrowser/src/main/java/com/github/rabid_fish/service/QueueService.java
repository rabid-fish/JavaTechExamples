package com.github.rabid_fish.service;

import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.model.QueueData;

public interface QueueService {

	MessageData getDetailedMessageDataForMessageId(String queueName, String messageId);
	Iterable<MessageData> getMessageDataIterable(String queueName);
	Iterable<MessageData> getMessageDataIterable(String queueName, String searchw);
	Iterable<QueueData> getQueueDataIterable();
	void removeMessage(String queueName, String messageId);
	
}
