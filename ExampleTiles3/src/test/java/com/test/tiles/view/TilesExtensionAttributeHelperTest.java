package com.test.tiles.view;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.test.tiles.view.TilesBoosterAttributeHelper;

public class TilesExtensionAttributeHelperTest {

	private static final String KEY = "viewJs";
	private static final String RESOURCE = "resource";
	private static final String EMPTY_RESOURCE = "";

	private static final String LAYOUT_KEY = "layout";
	private static final String LAYOUT_INDEXOF_DEFAULT = "default";
	private static final String LAYOUT_INDEXOF_OTHER = "/other/";
	private static final String LAYOUT_VALUE_DEFAULT = "layout.jsp";
	private static final String LAYOUT_VALUE_OTHER = "layoutOther.jsp";
	
	private static final String[][] LAYOUT_DEFAULT = {
		{ LAYOUT_INDEXOF_DEFAULT, LAYOUT_VALUE_DEFAULT },
		{ LAYOUT_INDEXOF_OTHER, LAYOUT_VALUE_OTHER },
	};

	private static final String URI_FOR_DEFAULT = "/WEB-INF/view/hello/helloDisplay.jsp";
	private static final String URI_FOR_OTHER = "/WEB-INF/view/other/helloDisplay.jsp";
	
	private TilesBoosterAttributeHelper helper;
	private Map<String, Object> attributeMap;
	private Set<String> resourcePaths;

	@Before
	public void setUp() {
		helper = new TilesBoosterAttributeHelper(LAYOUT_DEFAULT);
		attributeMap = new HashMap<String, Object>();
		resourcePaths = new HashSet<String>();
	}
	
	@Test
	public void testValidateLayoutsArray() {
		helper.validateLayoutsArray();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateLayoutsArrayWithNull() {
		helper = new TilesBoosterAttributeHelper(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateLayoutsArrayMissingDefault() {
		String[][] layouts = new String[][]{{ LAYOUT_INDEXOF_OTHER, LAYOUT_VALUE_DEFAULT }};
		helper = new TilesBoosterAttributeHelper(layouts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateLayoutsArrayWithBadArrayLength1() {
		String[][] layouts = new String[][]{{ LAYOUT_INDEXOF_DEFAULT, "" }};
		helper = new TilesBoosterAttributeHelper(layouts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateLayoutsArrayWithBadArrayLength2() {
		String[][] layouts = new String[][]{{ LAYOUT_INDEXOF_DEFAULT }};
		helper = new TilesBoosterAttributeHelper(layouts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateLayoutsArrayWithBadArrayLength3() {
		String[][] layouts = new String[][]{{ LAYOUT_INDEXOF_DEFAULT, LAYOUT_VALUE_DEFAULT, "extra" }};
		helper = new TilesBoosterAttributeHelper(layouts);
	}

	@Test
	public void testPutLayoutInMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		helper.putLayoutInMap(map, URI_FOR_DEFAULT);
		assertTrue(map.get(LAYOUT_KEY).equals(LAYOUT_VALUE_DEFAULT));
	}

	@Test
	public void testPutLayoutInMapForNonDefaultUri() {
		Map<String, Object> map = new HashMap<String, Object>();
		helper.putLayoutInMap(map, URI_FOR_OTHER);
		assertTrue(map.get(LAYOUT_KEY).equals(LAYOUT_VALUE_OTHER));
	}

	@Test
	public void testPutAttribute() {
		resourcePaths.add(RESOURCE);
		helper.putAttribute(attributeMap, resourcePaths, KEY, RESOURCE);
		Assert.assertEquals(RESOURCE, attributeMap.get(KEY));
	}

	@Test
	public void testPutAttributeWithNonmatchingKey() {
		helper.putAttribute(attributeMap, resourcePaths, KEY, RESOURCE);
		Assert.assertEquals(EMPTY_RESOURCE, attributeMap.get(KEY));
	}

}
