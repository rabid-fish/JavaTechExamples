package com.github.rabid_fish.jms.callback;

import static org.junit.Assert.assertTrue;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.QueueConfigViewHelper;
import com.github.rabid_fish.model.MessageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class CallbackViewTest {
	
	@Autowired
	QueueConfigViewHelper configViewHelper;

	@Test
	public void testSetMessage() throws JMSException {
	
		CallbackView callback = new CallbackView(configViewHelper.getQueueConfigDetailView());
		
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn(CallbackTest.MESSAGE_TEXT);
		MessageData messageData = new MessageData();
		callback.setMessageBody(message, messageData);
		
		assertTrue(messageData.getBody().equals(CallbackTest.MESSAGE_TEXT));
	}

}
