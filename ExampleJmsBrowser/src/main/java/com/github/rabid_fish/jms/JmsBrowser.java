package com.github.rabid_fish.jms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.github.rabid_fish.config.ConfigHelper;
import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.model.MessageData;

@Component
public class JmsBrowser {

	public static final Logger LOG = LoggerFactory.getLogger(JmsBrowser.class);
	
	private static final int MAX_TIMEOUT = 100000;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private ActiveMqJmxBrowser jmxBrowser;
	
	@Autowired
	private ConfigHelper configHelper;
	
	public List<MessageData> browseTopMessages(ConfigQueue configQueue) {
		
		JmsBrowserCallback callback = new JmsBrowserCallback(configQueue);
		List<MessageData> list = jmsTemplate.browseSelected(configQueue.getName(), "", callback);
		return list;
	}
	
	public List<List<MessageData>> browseTopMessagesForAllQueues() {
		
		List<List<MessageData>> listOfListOfQueueMessage = new ArrayList<List<MessageData>>();
		
		for (ConfigQueue configQueue : configHelper.getConfigQueueArray()) {
			List<MessageData> top3Messages = browseTopMessages(configQueue);
			listOfListOfQueueMessage.add(top3Messages);
		}
		
		return listOfListOfQueueMessage;
	}
	
	public void putMessage(String queueName, String messageText) {
		
		final String messageTextFinal = messageText;
		
		jmsTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
            	TextMessage message = session.createTextMessage(messageTextFinal);
            	session.commit();
				return message;
            }
        });
		
	}

	public void purgeAllQueues() throws JMSException {
		
		for (ConfigQueue configQueue : configHelper.getConfigQueueArray()) {
			
			String queueName = configQueue.getName();
			
			// Note that determining queuesize is necessary to purging
			// the queue and not pay a wait penalty.  This act couples
			// JmsBrowser to ActiveMQ unfortunately.
			JmsQueueStats queueStats = jmxBrowser.getQueueStats(queueName);
			Long queueSize = queueStats.getQueueSize();
			
			purgeQueue(queueName, queueSize);
		}
	}
	
	public void purgeQueue(String queueName, long maxMessageCount) throws JMSException {

		LOG.info("Purging queue " + queueName + ", max message count of: " + maxMessageCount);
		long messageCount = 0;
		long start = new Date().getTime();
		
		while (true) {
			Message message = jmsTemplate.receive(queueName);
			if (message == null) {
				break;
			}
			message.acknowledge();
			
			long diff = new Date().getTime() - start;
			LOG.debug(" removed message in " + diff + " milliseconds");
			
			if (++messageCount >= maxMessageCount) {
				LOG.debug("Hit max message count");
				break;
			}

			if (diff >= MAX_TIMEOUT) {
				throw new RuntimeException("Unable to purge all messages in queue, reached timeout");
			}
		}
	}
}
