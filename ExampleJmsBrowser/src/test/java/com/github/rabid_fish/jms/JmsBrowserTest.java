package com.github.rabid_fish.jms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.QueueConfigList;
import com.github.rabid_fish.config.QueueConfigView;
import com.github.rabid_fish.config.QueueConfigViewHelper;
import com.github.rabid_fish.config.QueueConfigListHelper;
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
	private QueueConfigListHelper configHelper;
	
	@Autowired
	private QueueConfigViewHelper configDetailViewHelper;
	
	private QueueConfigList defaultQueueConfig;
	
	@Before
	public void setUp() {
		defaultQueueConfig = configHelper.getQueueConfigArray()[0];
	}
	
	@Test
	public void testBrowseTopMessage() {
		
		LOG.info("Running test");
		QueueConfigList queueConfig = configHelper.getQueueConfigArray()[0];
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
	public void testBrowseMessageInDetail() {

		QueueConfigList localConfig = cloneConfig(defaultQueueConfig, 1);
		List<MessageData> messageDataList = browser.browseTopMessages(localConfig);
		String queueName = localConfig.getName();
		String messageId = messageDataList.get(0).getMessageId();
		QueueConfigView configDetailView = configDetailViewHelper.getQueueConfigDetailView();
		MessageData messageData = browser.browseMessageInDetail(configDetailView, queueName, messageId);
		
		assertNotNull(messageData);
	}
	
	@Test
	public void testDeleteMessage() {

		QueueConfigList localConfig = cloneConfig(defaultQueueConfig, 100);
		List<MessageData> messageDataListStart = browser.browseTopMessages(localConfig);
		
		String queueName = localConfig.getName();
		String messageId = messageDataListStart.get(0).getMessageId();
		browser.deleteMessage(queueName, messageId);
		
		List<MessageData> messageDataListEnd = browser.browseTopMessages(localConfig);
		
		assertTrue(messageDataListStart.size() > messageDataListEnd.size());
	}
	
	private QueueConfigList cloneConfig(QueueConfigList queueConfig, int maxMessageCount) {
		
		QueueConfigList clone = new QueueConfigList();
		clone.setColumns(queueConfig.getColumns());
		clone.setName(queueConfig.getName());
		clone.setMaxMessageCount(maxMessageCount);
		
		return clone;
	}
}
