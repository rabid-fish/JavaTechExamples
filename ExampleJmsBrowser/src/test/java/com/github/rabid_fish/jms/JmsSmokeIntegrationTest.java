package com.github.rabid_fish.jms;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class JmsSmokeIntegrationTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testJmsConnection() {
		
		JmsTemplate jms = applicationContext.getBean(JmsTemplate.class);
		String message = "Example as of " + new Date();
		jms.convertAndSend("ExampleJMS.Queue", message);
	}
}
