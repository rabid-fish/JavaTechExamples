package com.github.rabid_fish.config.helper;

import com.github.rabid_fish.config.QueueConfigList;

public class QueueConfigListHelper extends QueueConfigHelper {

	private QueueConfigList[] queueConfigListArray;
	
	public QueueConfigListHelper(String resourcePath) {
		queueConfigListArray = getConfigFromResourcePath(QueueConfigList[].class, resourcePath);
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

	void setQueueConfigLocalValues(QueueConfigList[] queueConfigListArray) {
		this.queueConfigListArray = queueConfigListArray;
	}
	
}
