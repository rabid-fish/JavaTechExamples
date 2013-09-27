package com.github.rabid_fish.config.helper;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public abstract class QueueConfigHelper {
	
	<T> T getConfigFromResourcePath(Class<T> clazz, String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			T mappedObject = mapper.readValue(stream, clazz);
			return mappedObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
