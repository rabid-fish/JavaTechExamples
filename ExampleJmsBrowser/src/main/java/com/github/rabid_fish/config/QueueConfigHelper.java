package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigHelper {

	private QueueConfig[] queueConfigArray = null;
	
	public QueueConfigHelper(String resourcePath) {
		setConfigQueueArray(resourcePath);
	}
	
	private void setConfigQueueArray(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setConfigQueueArray(mapper.readValue(stream, QueueConfig[].class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public QueueConfig getConfigQueueForQueueName(String queueName) {
		
		QueueConfig queueConfigForQueueName = null;
		for (QueueConfig queueConfigElement : queueConfigArray) {
			if (queueConfigElement.getName().equals(queueName)) {
				queueConfigForQueueName = queueConfigElement;
				break;
			}
		}
		
		return queueConfigForQueueName;
	}

	public QueueConfig[] getConfigQueueArray() {
		return queueConfigArray;
	}

	public void setConfigQueueArray(QueueConfig[] queueConfigArray) {
		this.queueConfigArray = queueConfigArray;
	}
	
}
