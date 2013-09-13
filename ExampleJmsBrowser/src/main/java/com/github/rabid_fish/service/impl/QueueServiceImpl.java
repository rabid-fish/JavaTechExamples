package com.github.rabid_fish.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabid_fish.config.ConfigHelper;
import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.jms.JmsBrowser;
import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.service.QueueService;

@Service
public class QueueServiceImpl implements QueueService {

	@Autowired
	JmsBrowser browser;
	
	ConfigHelper helper = new ConfigHelper("/json/queueConfig.json");
	
	@Override
	public Iterable<ConfigQueue> getQueueConfigIterable() {
		return Arrays.asList(helper.getConfigQueueArray());
	}

	@Override
	public Iterable<MessageData> getMessageDataIterable() {
//		return browser.browseTop3(configQueue);
		return null;
	}

}
