package com.github.rabid_fish.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.QueueConfigListHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class ActiveMqJmxBrowserTest {

	public static final Logger LOG = LoggerFactory.getLogger(ActiveMqJmxBrowserTest.class);
	
	@Autowired
	private ActiveMqJmxBrowser stats;
	
	@Autowired
	private QueueConfigListHelper configHelper;
	
	@Test
	public void test() {
		String queueName = configHelper.getQueueConfigListArray()[0].getName();
		JmsQueueStats queueStats = stats.getQueueStats(queueName);
		LOG.info("Queue depth for " + queueName + ": " + queueStats.getQueueSize());
	}
	
}
