package com.github.rabid_fish.config;

import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.github.rabid_fish.load.MessageLoad;
import com.github.rabid_fish.load.MessageLoadHelper;

public class ConfigHelperTest {

	private static final String QUEUE_CONFIG_JSON = "/json/queueConfig.json";
	private static final String QUEUE_LOAD_JSON = "/json/queueLoad.json";
	
	ConfigHelper helper = null;

	@Before
	public void setUp() {
		helper = new ConfigHelper(QUEUE_CONFIG_JSON);
	}
	
	@Test
	public void testHasQueueConfigItem() {
		assertTrue(helper.getConfigQueueArray().length > 0);
	}

	@Test
	public void testConfigQueueRegexHitsQueueLoadMessage() {
		
		ConfigQueue[] configQueueArray = new ConfigHelper(QUEUE_CONFIG_JSON).getConfigQueueArray();
		String regex = configQueueArray[0].getColumns().get(2).getRegex();
		
		MessageLoadHelper messageLoadHelper = new MessageLoadHelper(QUEUE_LOAD_JSON);
		MessageLoad messageLoad = messageLoadHelper.getMessageLoadArray()[0];
		String text = messageLoad.getText();
		
		Matcher matcher = Pattern.compile(regex).matcher(text);
		if (!matcher.find()) {
			text = "Not found";
		} else {
			text = matcher.group(0) + " : " + matcher.group(1);
		}
		
		System.out.println(text);
	}
}
