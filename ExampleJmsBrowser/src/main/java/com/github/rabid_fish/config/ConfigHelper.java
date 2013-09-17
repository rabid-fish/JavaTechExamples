package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class ConfigHelper {

	private ConfigQueue[] configQueueArray = null;
	
	public ConfigHelper(String resourcePath) {
		setConfigQueueArray(resourcePath);
	}
	
	private void setConfigQueueArray(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setConfigQueueArray(mapper.readValue(stream, ConfigQueue[].class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public ConfigQueue getConfigQueueForQueueName(String queueName) {
		
		ConfigQueue configQueueForQueueName = null;
		for (ConfigQueue configQueueElement : configQueueArray) {
			if (configQueueElement.getName().equals(queueName)) {
				configQueueForQueueName = configQueueElement;
				break;
			}
		}
		
		return configQueueForQueueName;
	}

	public ConfigQueue[] getConfigQueueArray() {
		return configQueueArray;
	}

	public void setConfigQueueArray(ConfigQueue[] configQueueArray) {
		this.configQueueArray = configQueueArray;
	}
	
}
