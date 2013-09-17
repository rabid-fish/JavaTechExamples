package com.github.rabid_fish.jms;

import javax.management.ObjectName;

public class JmsQueueStats {

	private Long QueueSize;
	private Long ConsumerCount;
	private Long ProducerCount;
	private ObjectName[] Subscriptions;
	private Integer MemoryPercentUsage;
	private Float MemoryUsagePortion;
	
	public Long getQueueSize() {
		return QueueSize;
	}
	public void setQueueSize(Long queueSize) {
		QueueSize = queueSize;
	}
	public Long getConsumerCount() {
		return ConsumerCount;
	}
	public void setConsumerCount(Long consumerCount) {
		ConsumerCount = consumerCount;
	}
	public Long getProducerCount() {
		return ProducerCount;
	}
	public void setProducerCount(Long producerCount) {
		ProducerCount = producerCount;
	}
	public ObjectName[] getSubscriptions() {
		return Subscriptions;
	}
	public void setSubscriptions(ObjectName[] subscriptions) {
		Subscriptions = subscriptions;
	}
	public Integer getMemoryPercentUsage() {
		return MemoryPercentUsage;
	}
	public void setMemoryPercentUsage(Integer memoryPercentUsage) {
		MemoryPercentUsage = memoryPercentUsage;
	}
	public Float getMemoryUsagePortion() {
		return MemoryUsagePortion;
	}
	public void setMemoryUsagePortion(Float memoryUsagePortion) {
		MemoryUsagePortion = memoryUsagePortion;
	}
}
