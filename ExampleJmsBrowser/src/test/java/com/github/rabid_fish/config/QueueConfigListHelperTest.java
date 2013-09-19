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
import com.github.rabid_fish.util.CommonUtil;

public class QueueConfigListHelperTest {

	public static final Logger LOG = LoggerFactory.getLogger(QueueConfigListHelperTest.class);
	QueueConfigListHelper helper = null;

	@Before
	public void setUp() {
		helper = new QueueConfigListHelper(CommonUtil.QUEUE_CONFIG_LIST_JSON);
	}
	
	@Test
	public void testHasQueueConfigItem() {
		assertTrue(helper.getQueueConfigArray().length > 0);
	}
	
	@Test
	public void testConfigQueueRegexHitsQueueLoadMessage() {
		
		QueueConfigList[] queueConfigArray = new QueueConfigListHelper(CommonUtil.QUEUE_CONFIG_LIST_JSON).getQueueConfigArray();
		String regex = queueConfigArray[0].getColumns().get(2).getRegex();
		
		MessageLoadHelper messageLoadHelper = new MessageLoadHelper(CommonUtil.QUEUE_LOAD_JSON);
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
		
		QueueConfigList queueConfig = helper.getQueueConfigArray()[0];
		QueueConfigList result = helper.getQueueConfigForQueueName(queueConfig.getName());
		assertTrue(result == queueConfig);
	}
	
	@Test
	public void testGetConfigQueueForQueueNameWithBadName() {
		
		QueueConfigList result = helper.getQueueConfigForQueueName("");
		assertNull(result);
	}
}
