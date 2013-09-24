package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigSearchHelper {

	private QueueConfigView queueConfigView = null;
	
	public QueueConfigSearchHelper(String resourcePath) {
		setQueueConfig(resourcePath);
	}

	public void setQueueConfig(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setQueueConfigDetailView(mapper.readValue(stream, QueueConfigView.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public QueueConfigView getQueueConfigDetailView() {
		return queueConfigView;
	}

	public void setQueueConfigDetailView(QueueConfigView queueConfigView) {
		this.queueConfigView = queueConfigView;
	}
	
}
