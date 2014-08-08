package com.github.rabid_fish.menu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class MenuHelper {

	private MenuItem[] menuItems = null;
	private Map<String, MenuItem> mapUrlToMenuItem = new HashMap<String, MenuItem>();
	private Map<String, MenuItem> mapNameToMenuItem = new HashMap<String, MenuItem>();
	private Map<String, MenuItem> mapUrlToParent = new HashMap<String, MenuItem>();
	private List<MenuItem> index = new ArrayList<MenuItem>();
	
	public MenuHelper(String resourcePath) {
		setMenuItems(resourcePath);
		fillMaps(menuItems, 0);
		mapChildren(menuItems);
		createIndexAndMapDepth(menuItems[0], 0);
	}
	
	protected void setMenuItems(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			menuItems = mapper.readValue(stream, MenuItem[].class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void fillMaps(MenuItem[] menuItems, int depth) {
		
		for (MenuItem menuItem : menuItems) {
			mapUrlToMenuItem.put(menuItem.getUrl(), menuItem);
			mapNameToMenuItem.put(menuItem.getName(), menuItem);
		}
	}

	protected void mapChildren(MenuItem[] menuItems) {

		for (MenuItem menuItem : menuItems) {
			List<String> children = menuItem.getChildren();
			for (String child : children) {
				MenuItem childMenuItem = mapNameToMenuItem.get(child);
				mapUrlToParent.put(childMenuItem.getUrl(), menuItem);
			}
		}
	}
	
	protected void createIndexAndMapDepth(MenuItem menuItem, int depth) {
		
		menuItem.setDepth(depth);
		index.add(menuItem);
		
		for (String child : menuItem.getChildren()) {
			MenuItem childMenuItem = mapNameToMenuItem.get(child);
			if (childMenuItem == null) continue;
			createIndexAndMapDepth(childMenuItem, depth + 1);
		}
	}
	
	public List<MenuItem> listParentMenuItemsForUrl(String url) {
		
		MenuItem menuItem = mapUrlToMenuItem.get(url);
		List<MenuItem> list = new ArrayList<MenuItem>();
		
		if (menuItem == null) {
			return list;
		}
		
		while (true) {
			menuItem = mapUrlToParent.get(menuItem.getUrl());
			if (menuItem == null) {
				break;
			}
			list.add(0, menuItem);
		}
		
		return list;
	}
	
	public List<MenuItem> getIndex() {
		return index;
	}

	public MenuItem getMenuItemForUrl(String url) {
		MenuItem menuItem = mapUrlToMenuItem.get(url);
		return menuItem;
	}
	
	public String getTitleForUrl(String url) {
		MenuItem menuItem = mapUrlToMenuItem.get(url);
		return menuItem == null ? null : menuItem.getTitle();
	}
	
	public String getNameForUrl(String url) {
		MenuItem menuItem = mapUrlToMenuItem.get(url);
		return menuItem == null ? null : menuItem.getName();
	}
}
