package com.github.rabid_fish.config.helper;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rabid_fish.config.QueueConfigList;
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
		helper = new QueueConfigListHelper(CommonUtil.QUEUE_CONFIG_LIST_JSON);
		assertTrue(helper.getQueueConfigListArray().length > 0);
	}
	
	@Test
	public void testConfigQueueRegexHitsQueueLoadMessage() {
		
		QueueConfigList[] queueConfigListArray = new QueueConfigListHelper(CommonUtil.QUEUE_CONFIG_LIST_JSON).getQueueConfigListArray();
		String regex = queueConfigListArray[0].getColumns().get(2).getRegex();
		
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
		
		QueueConfigList queueConfigList = helper.getQueueConfigListArray()[0];
		QueueConfigList result = helper.getQueueConfigForQueueName(queueConfigList.getName());
		assertTrue(result == queueConfigList);
	}
	
	@Test
	public void testGetConfigQueueForQueueNameWithBadName() {
		
		QueueConfigList result = helper.getQueueConfigForQueueName("");
		assertNull(result);
	}
}
