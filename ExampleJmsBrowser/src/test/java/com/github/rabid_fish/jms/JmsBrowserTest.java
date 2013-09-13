package com.github.rabid_fish.jms;

import static org.junit.Assert.*;

import java.util.List;

import javax.jms.JMSException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.ConfigHelper;
import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.model.MessageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class JmsBrowserTest {

	@Autowired
	private JmsBrowser browser;
	
	private ConfigQueue[] configQueueArray = new ConfigHelper("/json/queueConfig.json").getConfigQueueArray();
	
	@Before
	public void setUp() {
		browser.loadMessages();
	}
	
	@After
	public void tearDown() throws JMSException {
		browser.purgeAllQueues(configQueueArray);
	}
	
	@Test
	public void testBrowseTop3() {
		
		List<MessageData> messageDataList = browser.browseTop3(configQueueArray[0]);
		assertTrue(messageDataList.size() == 3);
		
		for (int i = 0; i < messageDataList.size(); i++) {
			
			List<String> dataTitleList = messageDataList.get(i).getDataTitleList();
			List<String> dataValueList = messageDataList.get(i).getDataValueList();
			String message = "";
			
			for (int j = 0; j < dataTitleList.size(); j++) {
				message += dataTitleList.get(j) + " : " + dataValueList.get(j) + "; ";
			}
			
			System.out.println(message);
		}
	}

}
