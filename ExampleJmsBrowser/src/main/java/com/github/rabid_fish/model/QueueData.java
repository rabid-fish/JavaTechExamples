package com.github.rabid_fish.model;

import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.jms.JmsQueueStats;

public class QueueData {

	private QueueConfig queueConfig;
	private JmsQueueStats jmsQueueStats;
	private Iterable<MessageData> messageDataList;
	
	public QueueConfig getQueueConfig() {
		return queueConfig;
	}
	public void setQueueConfig(QueueConfig queueConfig) {
		this.queueConfig = queueConfig;
	}
	public JmsQueueStats getJmsQueueStats() {
		return jmsQueueStats;
	}
	public void setJmsQueueStats(JmsQueueStats jmsQueueStats) {
		this.jmsQueueStats = jmsQueueStats;
	}
	public Iterable<MessageData> getMessageDataList() {
		return messageDataList;
	}
	public void setMessageDataList(Iterable<MessageData> messageDataList) {
		this.messageDataList = messageDataList;
	}
}
