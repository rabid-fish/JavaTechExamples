package com.github.rabid_fish.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/root-context.xml")
public class HistoryCleanupTest {

	@Autowired
	HistoryCleanup cleanup;
	
	@Test
	public void testHistoryCleanup() {
		cleanup.browse();
	}

}
