package com.github.rabid_fish.config;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class QueueConfigDetailViewHelper {

	private QueueConfigDetailView queueConfigDetailView = null;
	
	public QueueConfigDetailViewHelper(String resourcePath) {
		setQueueConfig(resourcePath);
	}

	public void setQueueConfig(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setQueueConfigDetailView(mapper.readValue(stream, QueueConfigDetailView.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public QueueConfigDetailView getQueueConfigDetailView() {
		return queueConfigDetailView;
	}

	public void setQueueConfigDetailView(QueueConfigDetailView queueConfigDetailView) {
		this.queueConfigDetailView = queueConfigDetailView;
	}
	
}
