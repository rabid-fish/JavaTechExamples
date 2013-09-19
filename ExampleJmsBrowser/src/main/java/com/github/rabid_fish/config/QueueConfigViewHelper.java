package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigViewHelper {

	private QueueConfigView queueConfigDetailView = null;
	
	public QueueConfigViewHelper(String resourcePath) {
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
		return queueConfigDetailView;
	}

	public void setQueueConfigDetailView(QueueConfigView queueConfigDetailView) {
		this.queueConfigDetailView = queueConfigDetailView;
	}
	
}