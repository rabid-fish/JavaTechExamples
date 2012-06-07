package com.test.tiles.view;

import junit.framework.Assert;

import org.junit.Test;

import com.test.tiles.view.TilesBoosterUri;

public class TilesExtensionUriTest {
	
	private static final String URI_VALID_PATH 		= "/TestTiles/WEB-INF/view/hello/helloDisplay.jsp";
	private static final String URI_INVALID_PATH 	= "bad_path_has_no_slashes";
	private static final String EMPTY_PATH 			= "";
	private static final String CONTEXT_PATH 		= "/TestTiles";
	private static final String CONTEXT_BASE 		= "/WEB-INF/view/hello";
	private static final String VIEW_CSS 			= CONTEXT_BASE + "/helloDisplay.css";
	private static final String VIEW_JS 			= CONTEXT_BASE + "/helloDisplay.js";

	@Test
	public void testSetProperties() {
		
		TilesBoosterUri uri = new TilesBoosterUri();
		uri.setProperties(URI_VALID_PATH, CONTEXT_PATH);
		
		Assert.assertNotNull(uri.getViewName());
		Assert.assertNotNull(uri.getViewJs());
		Assert.assertNotNull(uri.getViewCss());
		Assert.assertNotNull(uri.getUriBase());
		Assert.assertNotNull(uri.getContextBase());
		
		Assert.assertEquals(CONTEXT_BASE, uri.getContextBase());
		Assert.assertEquals(VIEW_CSS, uri.getViewCss());
		Assert.assertEquals(VIEW_JS, uri.getViewJs());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithEmptyUri() {
		new TilesBoosterUri().setProperties(EMPTY_PATH, CONTEXT_PATH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithEmptyContextPath() {
		new TilesBoosterUri().setProperties(URI_VALID_PATH, EMPTY_PATH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithInvalidPath() {
		new TilesBoosterUri().setProperties(URI_INVALID_PATH, CONTEXT_PATH);
	}
	
}
