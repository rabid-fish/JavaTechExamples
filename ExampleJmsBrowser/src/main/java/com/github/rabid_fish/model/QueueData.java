package com.github.rabid_fish.model;

import com.github.rabid_fish.config.QueueConfigList;
import com.github.rabid_fish.jms.JmsQueueStats;

public class QueueData {

	private QueueConfigList queueConfigList;
	private JmsQueueStats jmsQueueStats;
	private Iterable<MessageData> messageDataList;
	
	public QueueConfigList getQueueConfigList() {
		return queueConfigList;
	}
	public void setQueueConfigList(QueueConfigList queueConfigList) {
		this.queueConfigList = queueConfigList;
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
