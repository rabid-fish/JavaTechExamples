package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigHelper {

	private QueueConfig[] configQueueArray = null;
	
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
		
		QueueConfig configQueueForQueueName = null;
		for (QueueConfig configQueueElement : configQueueArray) {
			if (configQueueElement.getName().equals(queueName)) {
				configQueueForQueueName = configQueueElement;
				break;
			}
		}
		
		return configQueueForQueueName;
	}

	public QueueConfig[] getConfigQueueArray() {
		return configQueueArray;
	}

	public void setConfigQueueArray(QueueConfig[] configQueueArray) {
		this.configQueueArray = configQueueArray;
	}
	
}
