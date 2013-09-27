package com.github.rabid_fish.config.helper;

import com.github.rabid_fish.config.QueueConfigSearch;

public class QueueConfigSearchHelper extends QueueConfigHelper {

	private QueueConfigSearch queueConfigSearch = null;
	
	public QueueConfigSearchHelper(String resourcePath) {
		queueConfigSearch = getConfigFromResourcePath(QueueConfigSearch.class, resourcePath);
	}

	void setQueueConfigLocalValues(QueueConfigSearch queueConfigSearch) {
		this.queueConfigSearch = queueConfigSearch;
	}

	public QueueConfigSearch getQueueConfigSearch() {
		return queueConfigSearch;
	}
}
