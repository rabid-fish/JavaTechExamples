package com.github.rabid_fish.jms.callback;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.config.QueueConfigList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class CallbackListTest {

	CallbackList callback;
	
	@Before
	public void setUp() {
		QueueConfigList config = Mockito.mock(QueueConfigList.class);
		Mockito.when(config.getMaxMessageCount()).thenReturn(3);
		callback = new CallbackList(config);
	}
	
	@Test
	public void testCheckForEarlyBreakMessageCountLessThanMax() {
		boolean result = callback.checkForEarlyBreak(1);
		assertTrue(!result);
	}
	
	@Test
	public void testCheckForEarlyBreakMessageCountGreaterThanMax() {
		boolean result = callback.checkForEarlyBreak(4);
		assertTrue(result);
	}
	
	@Test
	public void testCheckForEarlyBreakMessageCountEqualToMax() {
		boolean result = callback.checkForEarlyBreak(3);
		assertTrue(result);
	}
	
}
