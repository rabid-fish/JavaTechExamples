package com.github.rabid_fish.jms.callback;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.github.rabid_fish.config.Configuration;
import com.github.rabid_fish.model.MessageData;

public class CallbackView extends Callback {

	CallbackView(Configuration config) {
		super(config);
	}

	@Override
	boolean checkForEarlyBreak(int size) {
		return false;
	}

	@Override
	void setMessageBody(Message message, MessageData messageData) throws JMSException {

		TextMessage textMessage = (TextMessage) message;
		String text = textMessage.getText();
		messageData.setBody(text);
	}

}
