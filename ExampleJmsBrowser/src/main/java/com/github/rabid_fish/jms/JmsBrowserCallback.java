package com.github.rabid_fish.jms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.BrowserCallback;

import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.config.QueueConfigColumn;
import com.github.rabid_fish.model.MessageData;

public class JmsBrowserCallback implements BrowserCallback<List<MessageData>> {

	private QueueConfig queueConfig;

	public JmsBrowserCallback(QueueConfig queueConfig) {
		this.queueConfig = queueConfig;
	}

	public List<MessageData> doInJms(Session session, QueueBrowser browser) throws JMSException {

		List<MessageData> list = new ArrayList<MessageData>();
		
		@SuppressWarnings("unchecked")
		Enumeration<Message> e = browser.getEnumeration();
		while (e.hasMoreElements()) {

			Message message = e.nextElement();
			List<QueueConfigColumn> columns = queueConfig.getColumns();
			MessageData messageData = new MessageData();

			appendMessageDataToList(message, columns, messageData);
			
			list.add(messageData);
			if (list.size() >= queueConfig.getMaxMessageCount()) break;
		}

		return list;
	}

	void appendMessageDataToList(Message message, List<QueueConfigColumn> columns, MessageData messageData)
			throws JMSException {
		
//		messageData.setMessageId(message.getJMSMessageID());
//		messageData.setTimestamp(message.getJMSTimestamp());
		
		for (QueueConfigColumn column : columns) {
			String value = getPropertyOrTextFromMessage(message, column.getTitle(), column.getProperty(), column.getRegex());
			messageData.getDataTitleList().add(column.getTitle());
			messageData.getDataValueList().add(value);
		}
	}

	String getPropertyOrTextFromMessage(Message message, String columnTitle, String propertyName, String regex) throws JMSException {

		String data = null;
		
		if (propertyName != null) {
			data = getPropertyFromMessage(message, propertyName);
		} else if (regex != null) {
			data = getTextFromMessage(message, regex);
		} else {
			throw new RuntimeException("Configuration for column '" + columnTitle + "' must contain a property or a regex but contains neither");
		}
		
		return data;
	}

	String getTextFromMessage(Message message, String regex) throws JMSException {
		
		String text = null;
		
		if (message instanceof TextMessage) {
			
			TextMessage textMessage = (TextMessage) message;
			text = textMessage.getText();
			
			Matcher matcher = Pattern.compile(regex).matcher(text);
			if (!matcher.find()) {
				text = "";
			} else {
				text = matcher.group(1);
			}
		}
		
		return text;
	}

	String getPropertyFromMessage(Message message, String propertyName) throws JMSException {
		
		String value;
		Object property = message.getObjectProperty(propertyName);
		
		if (property instanceof String) {
			value = (String) property;
		} else if (property instanceof Integer) {
			value = String.valueOf((Integer) property);
		} else if (property instanceof Long) {
			value = String.valueOf((Long) property);
		} else {
			value = property.toString();
		}
		
		return value;
	}
}