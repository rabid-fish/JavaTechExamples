package com.github.rabid_fish.menu;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.rabid_fish.menu.MenuHelper;
import com.github.rabid_fish.menu.MenuItem;

public class MenuHelperTest {

	private static final String TEST_URL = "/ExampleJsonBreadcrumb/app/menu/level2a";
	private static final String TEST_BAD_URL = "bad_url";

	MenuHelper helper = null;

	@Before
	public void setUp() {
		helper = new MenuHelper("/menu.json");
	}
	
	@Test
	public void testGetNameForUrl() {
		String name = helper.getNameForUrl(TEST_URL);
		assertEquals(name, "Level 2 A");
	}
	
	@Test
	public void testGetNameForUrlWithInvalidUrl() {
		String name = helper.getNameForUrl(TEST_BAD_URL);
		assertEquals(name, null);
	}
	
	@Test
	public void testGetTitleForUrl() {
		String title = helper.getTitleForUrl(TEST_URL);
		assertEquals(title, "ExampleJsonBreadcrumb Level 2 A");
	}
	
	@Test
	public void testGetTitleForUrlWithInvalidUrl() {
		String title = helper.getTitleForUrl(TEST_BAD_URL);
		assertEquals(title, null);
	}
	
	@Test
	public void testListCrumbsForUrl() {
		List<MenuItem> list = helper.listParentMenuItemsForUrl(TEST_URL);
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void testListCrumbsForUrlWithInvalidUrl() {
		List<MenuItem> list = helper.listParentMenuItemsForUrl(TEST_BAD_URL);
		assertTrue(list.size() == 0);
	}
	
	@Test
	public void testCheckIndex() {
		assertTrue(helper.getIndex().get(1).getName().equals("Level 1 A"));
		assertTrue(helper.getIndex().get(2).getName().equals("Level 2 A"));
	}
	
	@Test
	public void testGetDepthForUrl() {
		assertTrue(helper.getMenuItemForUrl(TEST_URL).getDepth() == 2);
	}
	
}
