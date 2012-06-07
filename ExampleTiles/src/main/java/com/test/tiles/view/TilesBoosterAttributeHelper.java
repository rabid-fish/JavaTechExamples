package com.test.tiles.view;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class TilesBoosterAttributeHelper {

	public static final String LAYOUT_DEFAULT_KEY = "default";
	private static final String ATTRIBUTE_NAME_LAYOUT = "layout";
	public static final String ATTRIBUTE_NAME_CSS = "viewCss";
	public static final String ATTRIBUTE_NAME_JS = "viewJs";

	private TilesBoosterUriCache cache = new TilesBoosterUriCache();
	
	private String[][] layouts;
	
	public TilesBoosterAttributeHelper(String[][] layouts) {
		this.layouts = layouts;
		validateLayoutsArray();
	}
	
	protected void validateLayoutsArray() {
		
		if (layouts == null || layouts.length == 0) {
			throw new IllegalArgumentException("Passed an empty layouts array in.");
		}
		
		boolean hasDefault = false;
		for (String layout[] : layouts) {
			if (layout[0].equals(LAYOUT_DEFAULT_KEY)) {
				hasDefault = true;
				break;
			}
		}
		
		if (hasDefault == false) {
			throw new IllegalArgumentException("Layouts array is missing a 'default' layout definition.");
		}

		for (String layout[] : layouts) {
			if (layout.length != 2 || 
				layout[0] == null || 
				layout[1] == null || 
				layout[0].length() == 0 || 
				layout[1].length() == 0) {

				throw new IllegalArgumentException("Layouts array contains a bad layout definition: " + layout.toString());
			}
		}
	}
	
	/**
	 * Determine the layout to use based on the path.
	 */
	public void putAttributeForLayout(Map<String, Object> attributeMap, HttpServletRequest request) {

		String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
		putLayoutInMap(attributeMap, uri);
	}

	// Created separate methods to get HttpServletRequest out of the method call for test purposes
	protected void putLayoutInMap(Map<String, Object> attributeMap, String uri) {

		String value = null;
		
		for (String[] layout : layouts) {
			if (layout[0].equals(LAYOUT_DEFAULT_KEY)) {
				value = layout[1];
			} else {
				if (uri != null && uri.indexOf(layout[0]) > -1) {
					value = layout[1];
					break;
				}
			}
		}
		
		attributeMap.put(ATTRIBUTE_NAME_LAYOUT, value);
	}

	/**
	 * Attempt to locate any sympathetic css or js files sitting alongside the jsp file, and
	 * add a tiles attribute for them if they exist.
	 * 
	 * Sympathetic files are in the same folder, are named the same, but have a different extension.
	 */
	@SuppressWarnings("rawtypes")
	public void putAttributesForSympatheticResources(ServletContext servletContext, Map<String, Object> attributeMap, String uriPath) {

		if (!uriPath.endsWith(".jsp")) {
			throw new RuntimeException("Requested view must end with .jsp extension in order for sympathetic resources to be loaded.");
		}

		TilesBoosterUri uri = cache.pullUriFromCache(uriPath, servletContext.getContextPath());
		Set resourcePaths = servletContext.getResourcePaths(uri.getContextBase());

		putAttributes(attributeMap, resourcePaths, uri);
	}
	

	@SuppressWarnings("rawtypes")
	protected void putAttributes(Map<String, Object> attributeMap, Set resourcePaths, TilesBoosterUri uri) {
		
		putAttribute(attributeMap, resourcePaths, ATTRIBUTE_NAME_JS, uri.getViewJs());
		putAttribute(attributeMap, resourcePaths, ATTRIBUTE_NAME_CSS, uri.getViewCss());
	}

	@SuppressWarnings("rawtypes")
	protected void putAttribute(Map<String, Object> attributeMap, Set resourcePaths, String key, String resource) {
		
		if (resourcePaths.contains(resource)) {
			attributeMap.put(key, resource);
		} else {
			attributeMap.put(key, "");
		}
	}


}
