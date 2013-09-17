package com.github.rabid_fish.load;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.rabid_fish.jms.JmsBrowser;

@Component
public class MessageLoader {
	
	public static final Logger LOG = LoggerFactory.getLogger(MessageLoader.class);
	
	@Autowired
	private JmsBrowser jmsBrowser;
	
	@PostConstruct
	public void setUpClass() {
		
		MessageLoad[] messageLoadArray = new MessageLoadHelper("/json/queueLoad.json").getMessageLoadArray();
		for (MessageLoad messageLoad : messageLoadArray) {
			jmsBrowser.putMessage(messageLoad.getQueueName(), messageLoad.getText());
		}
		LOG.info("Messages loaded");
	}
	
	@PreDestroy
	public void tearDownClass() throws JMSException {
		
		long start = new Date().getTime();
		jmsBrowser.purgeAllQueues();

		long end = new Date().getTime();
		long diff = end - start;
		LOG.info("Messages cleared in " + diff + " milliseconds");
	}
}