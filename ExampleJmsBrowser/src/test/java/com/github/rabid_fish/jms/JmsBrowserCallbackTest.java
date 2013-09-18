package com.github.rabid_fish.jms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.QueueConfigColumn;
import com.github.rabid_fish.config.QueueConfigHelper;
import com.github.rabid_fish.model.MessageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class JmsBrowserCallbackTest {

	private static final String MESSAGE_TEXT = "find\nresponse\nin this string";
	private static final String MESSAGE_TEXT_REGEX = "[A-Z]*(response)[A-Z]*";
	private static final String MESSAGE_TEXT_REGEX_NOMATCH = "[A-Z]*(nomatch)[A-Z]*";
	private static final String MESSAGE_TEXT_RESPONSE = "response";
	private static final String MESSAGE_TEXT_RESPONSE_NOMATCH = "";
	
	private static final String MESSAGE_PROPERTY_NAME_MESSAGEID = "JMSMessageID";
	private static final String MESSAGE_PROPERTY_VALUE_MESSAGEID = "1234-123-1234";
	private static final String MESSAGE_PROPERTY_NAME_TIMESTAMP = "JMSTimestamp";
	private static final Long MESSAGE_PROPERTY_VALUE_TIMESTAMP = new Date(1).getTime();
	private static final String MESSAGE_PROPERTY_RESULT_TIMESTAMP = "1969-12-31 06:00:00";
	
	private JmsBrowserCallback callback;
	
	@Autowired
	QueueConfigHelper configHelper;
	
	@Before
	public void setUp() {
		callback = new JmsBrowserCallback(configHelper.getConfigQueueArray()[0]);
	}
	
	@Test
	public void testAppendMessageDataToList() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(MESSAGE_TEXT);
		
		List<QueueConfigColumn> columns = new ArrayList<QueueConfigColumn>();
		columns.add(new QueueConfigColumn(null, null, MESSAGE_TEXT_REGEX));
		
		MessageData messageData = new MessageData();
		callback.appendMessageDataToList(message, columns, messageData);
		
		assertTrue(messageData.getDataValueList().size() == 1);
		assertTrue(messageData.getDataValueList().get(0).equals(MESSAGE_TEXT_RESPONSE));
	}

	@Test
	public void testSetMessageDataMessageId() throws JMSException {

		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getJMSMessageID()).thenReturn(MESSAGE_PROPERTY_VALUE_MESSAGEID);
		MessageData messageData = new MessageData();
		callback.setMessageDataMessageId(message, messageData);
		assertTrue(messageData.getMessageId().equals(MESSAGE_PROPERTY_VALUE_MESSAGEID));
	}

	@Test
	public void testGetPropertyOrTextFromMessage() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(MESSAGE_TEXT);
		String text = callback.getPropertyOrTextFromMessage(message, null, null, MESSAGE_TEXT_REGEX);
		
		assertTrue(text.equals(MESSAGE_TEXT_RESPONSE));
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetPropertyOrTextFromMessageWithNullParameters() throws JMSException {
		callback.getPropertyOrTextFromMessage(null, null, null, null);
	}
	
	@Test
	public void testGetTextFromMessage() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(MESSAGE_TEXT);
		String text = callback.getTextFromMessage(message, MESSAGE_TEXT_REGEX);
		
		assertTrue(text.equals(MESSAGE_TEXT_RESPONSE));
	}
	
	@Test
	public void testGetTextFromMessageWhenNoRegexMatch() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(MESSAGE_TEXT);
		String text = callback.getTextFromMessage(message, MESSAGE_TEXT_REGEX_NOMATCH);
		
		assertTrue(text.equals(MESSAGE_TEXT_RESPONSE_NOMATCH));
	}
	
	@Test
	public void testGetPropertyFromMessageForString() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getObjectProperty(MESSAGE_PROPERTY_NAME_MESSAGEID)).thenReturn(MESSAGE_PROPERTY_VALUE_MESSAGEID);
		String property = callback.getPropertyFromMessage(message, MESSAGE_PROPERTY_NAME_MESSAGEID);
		
		assertTrue(property.equals(MESSAGE_PROPERTY_VALUE_MESSAGEID));
	}
	
	@Test
	public void testGetPropertyFromMessageForLong() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getObjectProperty(MESSAGE_PROPERTY_NAME_TIMESTAMP)).thenReturn(MESSAGE_PROPERTY_VALUE_TIMESTAMP);
		String property = callback.getPropertyFromMessage(message, MESSAGE_PROPERTY_NAME_TIMESTAMP);
		
		assertTrue(property.equals(String.valueOf(MESSAGE_PROPERTY_RESULT_TIMESTAMP)));
	}

	@Test
	public void testGetPropertyLongCheckForJmsTimestamp() {
		
		String result = callback.getPropertyLongCheckForJmsTimestamp("JMSTimestamp", MESSAGE_PROPERTY_VALUE_TIMESTAMP);
		assertEquals(result, MESSAGE_PROPERTY_RESULT_TIMESTAMP);
	}

	@Test
	public void testGetPropertyLongCheckForJmsTimestampWithNotTimestamp() {
		
		String result = callback.getPropertyLongCheckForJmsTimestamp("Some property", MESSAGE_PROPERTY_VALUE_TIMESTAMP);
		assertEquals(result, "1");
	}
}
