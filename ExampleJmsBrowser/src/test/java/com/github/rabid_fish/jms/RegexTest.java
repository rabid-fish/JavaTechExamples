package com.github.rabid_fish.jms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.github.rabid_fish.config.ConfigHelper;
import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.load.MessageLoad;
import com.github.rabid_fish.load.MessageLoadHelper;

public class RegexTest {

	@Test
	public void test() {
		
		ConfigQueue[] configQueueArray = new ConfigHelper("/json/queueConfig.json").getConfigQueueArray();
		String regex = configQueueArray[0].getColumns().get(2).getRegex();
		
		MessageLoadHelper messageLoadHelper = new MessageLoadHelper("/json/queueLoad.json");
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
