package com.github.rabid_fish.jms;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Written with much help from:
 * http://www.sachinhandiekar.com/2011/12/get-activemq
 * -queue-size-using-mbean.html
 */
public class ActiveMqJmxBrowser {

	@Autowired
	private MBeanServerConnection mBeanServerConnectionFactoryBean;
	
	private String activeMqHost;
	
	@Autowired
	public ActiveMqJmxBrowser(String activeMqHost) {
		this.activeMqHost = activeMqHost;
	}

	public JmsQueueStats getQueueStats(String queueName) {
		
		JmsQueueStats stats = new JmsQueueStats();
		stats.setQueueSize((Long) getQueueStat(queueName, "QueueSize"));
		stats.setConsumerCount((Long) getQueueStat(queueName, "ConsumerCount"));
		stats.setProducerCount((Long) getQueueStat(queueName, "ProducerCount"));
		stats.setSubscriptions((javax.management.ObjectName[]) getQueueStat(queueName, "Subscriptions"));
		stats.setMemoryPercentUsage((Integer) getQueueStat(queueName, "MemoryPercentUsage"));
		stats.setMemoryUsagePortion((Float) getQueueStat(queueName, "MemoryUsagePortion"));
		
		return stats;
	}
	
	private Object getQueueStat(String queueName, String attribute) {
		
		Object value = null;
		
		try {
			String objectName = "org.apache.activemq:BrokerName=" + activeMqHost + ",Type=Queue,Destination=" + queueName;
			ObjectName objectNameRequest = new ObjectName(objectName);
			value = mBeanServerConnectionFactoryBean.getAttribute(objectNameRequest, attribute);
			
			return value;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}