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

import com.github.rabid_fish.config.QueueConfigList;
import com.github.rabid_fish.config.QueueConfigView;
import com.github.rabid_fish.config.helper.QueueConfigListHelper;
import com.github.rabid_fish.jms.callback.Callback;
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
	private QueueConfigListHelper configListHelper;
	
	public List<MessageData> browseTopMessages(QueueConfigList config) {
		
		Callback callback = Callback.getCallbackForConfig(config);
		List<MessageData> list = jmsTemplate.browseSelected(config.getName(), "", callback);
		return list;
	}
	
	public List<List<MessageData>> browseTopMessagesForAllQueues() {
		
		List<List<MessageData>> listOfListOfQueueMessage = new ArrayList<List<MessageData>>();
		
		for (QueueConfigList queueConfigList : configListHelper.getQueueConfigListArray()) {
			List<MessageData> top3Messages = browseTopMessages(queueConfigList);
			listOfListOfQueueMessage.add(top3Messages);
		}
		
		return listOfListOfQueueMessage;
	}
	
	public MessageData browseMessageInDetail(QueueConfigView config, String queueName, String messageId) {
		
		Callback callback = Callback.getCallbackForConfig(config);
		List<MessageData> messageDataList = jmsTemplate.browseSelected(queueName, "JMSMessageID='" + messageId + "'", callback);
		
		return messageDataList.get(0);
	}
	
	public void deleteMessage(String queueName, String messageId) {
		
		jmsTemplate.receiveSelected(queueName, "JMSMessageID='" + messageId + "'");
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
		
		for (QueueConfigList queueConfigList : configListHelper.getQueueConfigListArray()) {
			
			String queueName = queueConfigList.getName();
			
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
