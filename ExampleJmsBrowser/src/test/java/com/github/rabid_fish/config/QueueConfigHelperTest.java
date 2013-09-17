package com.github.rabid_fish.config;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rabid_fish.load.MessageLoad;
import com.github.rabid_fish.load.MessageLoadHelper;

public class QueueConfigHelperTest {

	public static final Logger LOG = LoggerFactory.getLogger(QueueConfigHelperTest.class);
	private static final String QUEUE_CONFIG_JSON = "/json/queueConfig.json";
	private static final String QUEUE_LOAD_JSON = "/json/queueLoad.json";
	
	QueueConfigHelper helper = null;

	@Before
	public void setUp() {
		helper = new QueueConfigHelper(QUEUE_CONFIG_JSON);
	}
	
	@Test
	public void testHasQueueConfigItem() {
		assertTrue(helper.getConfigQueueArray().length > 0);
	}

	@Test
	public void testConfigQueueRegexHitsQueueLoadMessage() {
		
		QueueConfig[] queueConfigArray = new QueueConfigHelper(QUEUE_CONFIG_JSON).getConfigQueueArray();
		String regex = queueConfigArray[0].getColumns().get(2).getRegex();
		
		MessageLoadHelper messageLoadHelper = new MessageLoadHelper(QUEUE_LOAD_JSON);
		MessageLoad messageLoad = messageLoadHelper.getMessageLoadArray()[0];
		String text = messageLoad.getText();
		
		Matcher matcher = Pattern.compile(regex).matcher(text);
		if (!matcher.find()) {
			text = "Not found";
		} else {
			text = matcher.group(0) + " : " + matcher.group(1);
		}
		
		LOG.info(text);
	}
	
	@Test
	public void testGetConfigQueueForQueueName() {
		
		QueueConfig queueConfig = helper.getConfigQueueArray()[0];
		QueueConfig result = helper.getConfigQueueForQueueName(queueConfig.getName());
		assertTrue(result == queueConfig);
	}
	
	@Test
	public void testGetConfigQueueForQueueNameWithBadName() {
		
		QueueConfig result = helper.getConfigQueueForQueueName("");
		assertNull(result);
	}
}
