package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigListHelper {

	private QueueConfigList[] queueConfigListArray = null;
	
	public QueueConfigListHelper(String resourcePath) {
		setQueueConfigArray(resourcePath);
	}
	
	private void setQueueConfigArray(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setQueueConfigListArray(mapper.readValue(stream, QueueConfigList[].class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public QueueConfigList getQueueConfigForQueueName(String queueName) {
		
		QueueConfigList queueConfigForQueueName = null;
		for (QueueConfigList queueConfigElement : queueConfigListArray) {
			if (queueConfigElement.getName().equals(queueName)) {
				queueConfigForQueueName = queueConfigElement;
				break;
			}
		}
		
		return queueConfigForQueueName;
	}

	public QueueConfigList[] getQueueConfigListArray() {
		return queueConfigListArray;
	}

	public void setQueueConfigListArray(QueueConfigList[] queueConfigListArray) {
		this.queueConfigListArray = queueConfigListArray;
	}
	
}
