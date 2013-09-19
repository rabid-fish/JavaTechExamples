package com.github.rabid_fish.config;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rabid_fish.util.CommonUtil;

public class QueueConfigViewHelperTest {

	@Test
	public void testConstructor() {
		QueueConfigViewHelper helper = new QueueConfigViewHelper(CommonUtil.QUEUE_CONFIG_VIEW_JSON);
		assertTrue(helper.getQueueConfigDetailView().getColumns().size() > 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testConstructorWithNullArgument() {
		new QueueConfigViewHelper(null);
	}
}
