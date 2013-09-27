package com.github.rabid_fish.config.helper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.github.rabid_fish.config.ConfigurationColumn;
import com.github.rabid_fish.util.CommonUtil;

public class QueueConfigSearchHelperTest {

	private static final String EXAMPLE_SEARCH_STRING = "contact";

	@Test
	public void testClone() {
		
		QueueConfigSearchHelper helper = new QueueConfigSearchHelper(CommonUtil.QUEUE_CONFIG_SEARCH_JSON);
		QueueConfigSearchHelper clone = helper.clone(EXAMPLE_SEARCH_STRING);
		
		List<ConfigurationColumn> columns = clone.getQueueConfigSearch().getColumns();
		ConfigurationColumn result = null;
		for (ConfigurationColumn column : columns) {
			if (column.getTitle().equals(QueueConfigSearchHelper.SEARCH_TITLE)) {
				result = column;
				break;
			}
		}
		
		assertNotNull(result);
		assertEquals(result.getRegex(), EXAMPLE_SEARCH_STRING);
	}

}
