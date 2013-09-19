package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigListHelper {

	private QueueConfigList[] queueConfigArray = null;
	
	public QueueConfigListHelper(String resourcePath) {
		setQueueConfigArray(resourcePath);
	}
	
	private void setQueueConfigArray(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setQueueConfigArray(mapper.readValue(stream, QueueConfigList[].class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public QueueConfigList getQueueConfigForQueueName(String queueName) {
		
		QueueConfigList queueConfigForQueueName = null;
		for (QueueConfigList queueConfigElement : queueConfigArray) {
			if (queueConfigElement.getName().equals(queueName)) {
				queueConfigForQueueName = queueConfigElement;
				break;
			}
		}
		
		return queueConfigForQueueName;
	}

	public QueueConfigList[] getQueueConfigArray() {
		return queueConfigArray;
	}

	public void setQueueConfigArray(QueueConfigList[] queueConfigArray) {
		this.queueConfigArray = queueConfigArray;
	}
	
}
