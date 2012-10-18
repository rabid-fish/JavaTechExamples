package com.github.rabid_fish.breadcrumb;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BreadcrumbHelperTest {

	private static final String TEST_URL = "/ExampleJsonBreadcrumb/app/breadcrumb/level2a";
	private static final String TEST_BAD_URL = "bad_url";

	BreadcrumbHelper helper = null;

	@Before
	public void setUp() {
		helper = new BreadcrumbHelper("/breadcrumb.json");
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
		List<Breadcrumb> list = helper.listParentCrumbsForUrl(TEST_URL);
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void testListCrumbsForUrlWithInvalidUrl() {
		List<Breadcrumb> list = helper.listParentCrumbsForUrl(TEST_BAD_URL);
		assertTrue(list.size() == 0);
	}
	
	@Test
	public void testGetIndex() {
		assertTrue(helper.getIndex().size() > 0);
	}
	
}
