package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigHelper {

	private QueueConfig[] queueConfigArray = null;
	
	public QueueConfigHelper(String resourcePath) {
		setQueueConfigArray(resourcePath);
	}
	
	private void setQueueConfigArray(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setQueueConfigArray(mapper.readValue(stream, QueueConfig[].class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public QueueConfig getQueueConfigForQueueName(String queueName) {
		
		QueueConfig queueConfigForQueueName = null;
		for (QueueConfig queueConfigElement : queueConfigArray) {
			if (queueConfigElement.getName().equals(queueName)) {
				queueConfigForQueueName = queueConfigElement;
				break;
			}
		}
		
		return queueConfigForQueueName;
	}

	public QueueConfig[] getQueueConfigArray() {
		return queueConfigArray;
	}

	public void setQueueConfigArray(QueueConfig[] queueConfigArray) {
		this.queueConfigArray = queueConfigArray;
	}
	
}
