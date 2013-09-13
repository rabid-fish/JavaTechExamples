package com.github.rabid_fish.jms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.load.MessageLoad;
import com.github.rabid_fish.load.MessageLoadHelper;
import com.github.rabid_fish.model.MessageData;

@Component
public class JmsBrowser {

	private static final int MAX_TIMEOUT = 10000;
	
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public List<MessageData> browseTop3(ConfigQueue configQueue) {
		
		JmsBrowserCallback callback = new JmsBrowserCallback(configQueue);
		List<MessageData> list = jmsTemplate.browseSelected(configQueue.getName(), "", callback);
		
		return list;
	}
	
	public List<List<MessageData>> browseMessagesForAllQueues(ConfigQueue[] configQueueArray) {
		
		List<List<MessageData>> listOfListOfQueueMessage = new ArrayList<List<MessageData>>();
		
		for (ConfigQueue configQueue : configQueueArray) {
			List<MessageData> top3Messages = browseTop3(configQueue);
			listOfListOfQueueMessage.add(top3Messages);
		}
		
		return listOfListOfQueueMessage;
	}
	
	public void putMessage(String queueName, String messageText) {
		
		final String messageTextFinal = messageText;
		
		jmsTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
            	TextMessage message = session.createTextMessage(messageTextFinal);
				return message;
            }
        });
	}

	public void purgeAllQueues(ConfigQueue[] configQueueArray) throws JMSException {
		
		for (ConfigQueue configQueue : configQueueArray) {
			purgeQueue(configQueue.getName());
		}
	}
	
	public void purgeQueue(String queueName) throws JMSException {
		
		long start = new Date().getTime();
		while (true) {
			Message message = jmsTemplate.receive(queueName);
			if (message == null) {
				break;
			}
			message.acknowledge();
			
			long diff = new Date().getTime() - start;
			if (diff >= MAX_TIMEOUT) {
				throw new RuntimeException("Unable to purge all messages in queue, reached timeout");
			}
			
		}
	}
	
	public void loadMessages() {
		
		MessageLoadHelper messageLoadHelper = new MessageLoadHelper("/json/queueLoad.json");
		MessageLoad[] messageLoadArray = messageLoadHelper.getMessageLoadArray();
		for (MessageLoad messageLoad : messageLoadArray) {
			putMessage(messageLoad.getQueueName(), messageLoad.getText());
		}
	}
}
