package com.github.rabid_fish.config.helper;

import com.github.rabid_fish.config.QueueConfigView;

public class QueueConfigViewHelper extends QueueConfigHelper {

	private QueueConfigView queueConfigView = null;
	
	public QueueConfigViewHelper(String resourcePath) {
		queueConfigView = getConfigFromResourcePath(QueueConfigView.class, resourcePath);
	}

	void setQueueConfigLocalValues(QueueConfigView queueConfigView) {
		this.queueConfigView = queueConfigView;
	}

	public QueueConfigView getQueueConfigDetailView() {
		return queueConfigView;
	}
}
