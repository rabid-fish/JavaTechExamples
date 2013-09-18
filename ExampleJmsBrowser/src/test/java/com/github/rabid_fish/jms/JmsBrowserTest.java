package com.github.rabid_fish.jms;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.ConfigurationColumn;
import com.github.rabid_fish.config.QueueConfig;
import com.github.rabid_fish.config.QueueConfigDetailView;
import com.github.rabid_fish.config.QueueConfigDetailViewHelper;
import com.github.rabid_fish.config.QueueConfigHelper;
import com.github.rabid_fish.load.MessageLoader;
import com.github.rabid_fish.model.MessageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class JmsBrowserTest {

	public static final Logger LOG = LoggerFactory.getLogger(JmsBrowserTest.class);
	
	@Autowired
	private JmsBrowser browser;
	
	@Autowired
	MessageLoader messageLoader;
	
	@Autowired
	private QueueConfigHelper configHelper;
	
	@Autowired
	private QueueConfigDetailViewHelper configDetailViewHelper;
	
	@Test
	public void testBrowseTopMessage() {
		
		LOG.info("Running test");
		QueueConfig queueConfig = configHelper.getQueueConfigArray()[0];
		queueConfig.setMaxMessageCount(3);
		List<MessageData> messageDataList = browser.browseTopMessages(queueConfig);
		
		LOG.info("Count of top messages on queue: " + messageDataList.size());
		assertTrue(messageDataList.size() == 3);
		
		for (int i = 0; i < messageDataList.size(); i++) {
			
			List<String> dataTitleList = messageDataList.get(i).getDataTitleList();
			List<String> dataValueList = messageDataList.get(i).getDataValueList();
			String message = "";
			
			for (int j = 0; j < dataTitleList.size(); j++) {
				message += dataTitleList.get(j) + " : " + dataValueList.get(j) + "; ";
			}
			
			LOG.info(message);
		}
	}
	
	@Test
	public void testBrowseSpecificMessage() {

		QueueConfig queueConfig = new QueueConfig();
		queueConfig.setColumns(new ArrayList<ConfigurationColumn>());
		queueConfig.setMaxMessageCount(1);
		queueConfig.setName("ExampleJMS.Queue.Contacts");
		
		List<MessageData> messageDataList = browser.browseTopMessages(queueConfig);
		
		String queueName = queueConfig.getName();
		String messageId = messageDataList.get(0).getMessageId();
		QueueConfigDetailView configDetailView = configDetailViewHelper.getQueueConfigDetailView();
		browser.browseMessageInDetail(configDetailView, queueName, messageId);
	}
}
