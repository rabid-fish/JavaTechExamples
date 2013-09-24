package com.github.rabid_fish.jms.callback;

import javax.jms.JMSException;
import javax.jms.Message;

import com.github.rabid_fish.config.Configuration;
import com.github.rabid_fish.config.QueueConfigList;
import com.github.rabid_fish.model.MessageData;

public class CallbackList extends Callback {

	CallbackList(Configuration config) {
		super(config);
	}

	@Override
	boolean checkForEarlyBreak(int size) {
		boolean condition = size >= ((QueueConfigList) getConfig()).getMaxMessageCount();
		return condition;
	}

	@Override
	void setMessageBody(Message message, MessageData messageData) throws JMSException {
		// do nothing
	}

}
