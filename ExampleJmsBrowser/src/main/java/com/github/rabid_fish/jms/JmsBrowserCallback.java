package com.github.rabid_fish.jms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.github.rabid_fish.config.Configuration;
import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.config.ConfigurationColumn;
import com.github.rabid_fish.config.QueueConfigDetailView;
import com.github.rabid_fish.model.MessageData;

public class JmsBrowserCallback implements BrowserCallback<List<MessageData>> {

	private Configuration config;

	public JmsBrowserCallback(Configuration config) {
		this.config = config;
	}

	public List<MessageData> doInJms(Session session, QueueBrowser browser) throws JMSException {

		List<MessageData> list = new ArrayList<MessageData>();
		
		@SuppressWarnings("unchecked")
		Enumeration<Message> e = browser.getEnumeration();
		while (e.hasMoreElements()) {
			
			Message message = e.nextElement();
			List<ConfigurationColumn> columns = config.getColumns();
			MessageData messageData = new MessageData();
			
			setMessageDataMessageId(message, messageData);
			setMessageBody(message, messageData);
			appendMessageDataToList(message, columns, messageData);
			
			list.add(messageData);
			if (checkForEarlyBreak(list.size())) break;
		}

		return list;
	}
	
	private boolean checkForEarlyBreak(int size) {
		
		if (!(config instanceof QueueConfig)) {
			return false;
		}
		
		boolean condition = size >= ((QueueConfig) config).getMaxMessageCount();
		return condition;
	}

	void setMessageDataMessageId(Message message, MessageData messageData) throws JMSException {
		messageData.setMessageId(message.getJMSMessageID());
	}

	void setMessageBody(Message message, MessageData messageData) throws JMSException {
		
		if (!(config instanceof QueueConfigDetailView)) {
			messageData.setBody(null);
			return;
		}
		
		TextMessage textMessage = (TextMessage) message;
		String text = textMessage.getText();
		messageData.setBody(text);
	}

	void appendMessageDataToList(Message message, List<ConfigurationColumn> columns, MessageData messageData)
			throws JMSException {
		
		for (ConfigurationColumn column : columns) {
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
		
		if (property == null) {
			value = null;
		} else if (property instanceof String) {
			value = (String) property;
		} else if (property instanceof Integer) {
			value = String.valueOf((Integer) property);
		} else if (property instanceof Long) {
			value = getPropertyLongCheckForJmsTimestamp(propertyName, property);
		} else {
			value = property.toString();
		}
		
		return value;
	}

	String getPropertyLongCheckForJmsTimestamp(String propertyName, Object property) {
		
		String value;
		
		if (propertyName.equals("JMSTimestamp")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date propertyDate = new Date((Long) property);
			value = sdf.format(propertyDate);
			return value;
		}
		
		value = String.valueOf((Long) property);
		
		return value;
	}
}