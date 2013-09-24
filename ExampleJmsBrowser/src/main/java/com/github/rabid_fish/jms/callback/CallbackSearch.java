package com.github.rabid_fish.jms.callback;

import javax.jms.JMSException;
import javax.jms.Message;

import com.github.rabid_fish.config.Configuration;
import com.github.rabid_fish.model.MessageData;

public class CallbackSearch extends Callback {

	CallbackSearch(Configuration config) {
		super(config);
	}

	@Override
	boolean checkForEarlyBreak(int size) {
		return false;
	}

	@Override
	void setMessageBody(Message message, MessageData messageData) throws JMSException {
		// do nothing
	}

}
