package com.github.rabid_fish.jms.callback;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.Configuration;
import com.github.rabid_fish.config.ConfigurationColumn;
import com.github.rabid_fish.model.MessageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class CallbackTest {

	public static final String MESSAGE_TEXT = "find\nresponse\nin this string";
	public static final String MESSAGE_TEXT_REGEX = "[A-Z]*(response)[A-Z]*";
	public static final String MESSAGE_TEXT_REGEX_NOMATCH = "[A-Z]*(nomatch)[A-Z]*";
	public static final String MESSAGE_TEXT_RESPONSE = "response";
	public static final String MESSAGE_TEXT_RESPONSE_NOMATCH = "";
	
	public static final String MESSAGE_PROPERTY_NAME_MESSAGEID = "JMSMessageID";
	public static final String MESSAGE_PROPERTY_VALUE_MESSAGEID = "1234-123-1234";
	public static final String MESSAGE_PROPERTY_NAME_TIMESTAMP = "JMSTimestamp";
	public static final Long MESSAGE_PROPERTY_VALUE_TIMESTAMP = new Date(1).getTime();
	public static final String MESSAGE_PROPERTY_RESULT_TIMESTAMP = "1969-12-31 06:00:00";
	
	private Configuration config = new Configuration(){};
	private Callback callback = new JmsBrowserCallbackMock(config);
	
	public class JmsBrowserCallbackMock extends Callback {

		JmsBrowserCallbackMock(Configuration config) {
			super(config);
		}

		@Override
		boolean checkForEarlyBreak(int size) {
			return false;
		}

		@Override
		void setMessageBody(Message message, MessageData messageData) throws JMSException {
		}
	}
	
	@Test
	public void testAppendMessageDataToList() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(CallbackTest.MESSAGE_TEXT);
		
		List<ConfigurationColumn> columns = new ArrayList<ConfigurationColumn>();
		columns.add(new ConfigurationColumn(null, null, CallbackTest.MESSAGE_TEXT_REGEX));
		
		MessageData messageData = new MessageData();
		callback.appendMessageDataToList(message, columns, messageData);
		
		assertTrue(messageData.getDataValueList().size() == 1);
		assertTrue(messageData.getDataValueList().get(0).equals(CallbackTest.MESSAGE_TEXT_RESPONSE));
	}

	@Test
	public void testSetMessageDataMessageId() throws JMSException {

		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getJMSMessageID()).thenReturn(CallbackTest.MESSAGE_PROPERTY_VALUE_MESSAGEID);
		MessageData messageData = new MessageData();
		callback.setMessageDataMessageId(message, messageData);
		
		assertTrue(messageData.getMessageId().equals(CallbackTest.MESSAGE_PROPERTY_VALUE_MESSAGEID));
	}
	
	@Test
	public void testSetMessageBodyWithQueueConfig() throws JMSException {
		
		MessageData messageData = new MessageData();
		callback.setMessageBody(null, messageData);
		assertTrue(messageData.getBody() == null);
	}

	@Test
	public void testGetPropertyOrTextFromMessage() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(CallbackTest.MESSAGE_TEXT);
		String text = callback.getPropertyOrTextFromMessage(message, null, null, CallbackTest.MESSAGE_TEXT_REGEX);
		
		assertTrue(text.equals(CallbackTest.MESSAGE_TEXT_RESPONSE));
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetPropertyOrTextFromMessageWithNullParameters() throws JMSException {
		callback.getPropertyOrTextFromMessage(null, null, null, null);
	}
	
	@Test
	public void testGetTextFromMessage() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(CallbackTest.MESSAGE_TEXT);
		String text = callback.getTextFromMessage(message, CallbackTest.MESSAGE_TEXT_REGEX);
		
		assertTrue(text.equals(CallbackTest.MESSAGE_TEXT_RESPONSE));
	}
	
	@Test
	public void testGetTextFromMessageWhenNoRegexMatch() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(CallbackTest.MESSAGE_TEXT);
		String text = callback.getTextFromMessage(message, CallbackTest.MESSAGE_TEXT_REGEX_NOMATCH);
		
		assertTrue(text.equals(CallbackTest.MESSAGE_TEXT_RESPONSE_NOMATCH));
	}
	
	@Test
	public void testGetPropertyFromMessageForString() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getObjectProperty(CallbackTest.MESSAGE_PROPERTY_NAME_MESSAGEID)).thenReturn(CallbackTest.MESSAGE_PROPERTY_VALUE_MESSAGEID);
		String property = callback.getPropertyFromMessage(message, CallbackTest.MESSAGE_PROPERTY_NAME_MESSAGEID);
		
		assertTrue(property.equals(CallbackTest.MESSAGE_PROPERTY_VALUE_MESSAGEID));
	}
	
	@Test
	public void testGetPropertyFromMessageForLong() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getObjectProperty(CallbackTest.MESSAGE_PROPERTY_NAME_TIMESTAMP)).thenReturn(CallbackTest.MESSAGE_PROPERTY_VALUE_TIMESTAMP);
		String property = callback.getPropertyFromMessage(message, CallbackTest.MESSAGE_PROPERTY_NAME_TIMESTAMP);
		
		assertTrue(property.equals(String.valueOf(CallbackTest.MESSAGE_PROPERTY_RESULT_TIMESTAMP)));
	}
	
	@Test
	public void testGetPropertyFromMessageForNull() throws JMSException {
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getObjectProperty(CallbackTest.MESSAGE_PROPERTY_NAME_MESSAGEID)).thenReturn(null);
		String property = callback.getPropertyFromMessage(message, CallbackTest.MESSAGE_PROPERTY_NAME_MESSAGEID);
		
		assertTrue(property == null);
	}

	@Test
	public void testGetPropertyLongCheckForJmsTimestamp() {
		
		String result = callback.getPropertyLongCheckForJmsTimestamp("JMSTimestamp", CallbackTest.MESSAGE_PROPERTY_VALUE_TIMESTAMP);
		assertEquals(result, CallbackTest.MESSAGE_PROPERTY_RESULT_TIMESTAMP);
	}

	@Test
	public void testGetPropertyLongCheckForJmsTimestampWithNotTimestamp() {
		
		String result = callback.getPropertyLongCheckForJmsTimestamp("Some property", CallbackTest.MESSAGE_PROPERTY_VALUE_TIMESTAMP);
		assertEquals(result, "1");
	}
}
