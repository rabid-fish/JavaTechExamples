package com.github.rabid_fish.jms;

import java.io.StringWriter;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class HistoryCleanup {

	private static Logger LOG = LoggerFactory.getLogger(HistoryCleanup.class);
	
	public class HistoryCallback implements BrowserCallback<Object> {

		@SuppressWarnings("unchecked")
		public Object doInJms(Session session, QueueBrowser browser) throws JMSException {
			
			Enumeration<Message> e = browser.getEnumeration();
			while (e.hasMoreElements()) {
				Message message = e.nextElement();
				
				StringWriter writer = new StringWriter();
				writer.write(" type: " + message.getJMSType());
				writer.write(", message id: " + message.getJMSMessageID());
				writer.write(", correlation id: " + message.getJMSCorrelationID());
//				writer.write(", timestamp: " + new Date(message.getJMSTimestamp()));
				writer.write(", timestamp: " + message.getJMSTimestamp());
				
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					writer.write(", text: " + textMessage.getText());
				}
				
				LOG.info(writer.toString());
			}
			
			return null;
		}
	}
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		System.out.println("success");
	}
	
	public void browse() {
		String queue = "ExampleJMS.Queue";
//		String selector = "JMSTimestamp > 1378915730417";
		String selector = "";
		jmsTemplate.browseSelected(queue, selector , new HistoryCallback());
//		jmsTemplate.receiveSelected(messageSelector);
	}
}
