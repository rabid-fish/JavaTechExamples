package com.github.rabid_fish.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueConfigDetailViewHelperTest {

	private static final String QUEUE_CONFIG_DETAIL_VIEW_JSON = "/json/queueConfigDetailView.json";
	
	@Test
	public void testConstructor() {
		QueueConfigDetailViewHelper helper = new QueueConfigDetailViewHelper(QUEUE_CONFIG_DETAIL_VIEW_JSON);
		assertTrue(helper.getQueueConfigDetailView().getColumns().size() > 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testConstructorWithNullArgument() {
		new QueueConfigDetailViewHelper(null);
	}
}
