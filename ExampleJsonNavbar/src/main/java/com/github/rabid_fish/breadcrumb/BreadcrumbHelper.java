package com.github.rabid_fish.breadcrumb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class BreadcrumbHelper {

	private Breadcrumb root = null;
	private Map<String, Breadcrumb> mapUrlToBreadcrumb = new HashMap<String, Breadcrumb>();
	private Map<String, Breadcrumb> mapUrlToParent = new HashMap<String, Breadcrumb>();
	private List<Breadcrumb> index = new ArrayList<Breadcrumb>();
	
	public BreadcrumbHelper(String resourcePath) {
		setRoot(resourcePath);
		fillMapsAndList(root, 0);
	}

	private void setRoot(String resourcePath) {

		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			root = mapper.readValue(stream, Breadcrumb.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void fillMapsAndList(Breadcrumb breadcrumb, int depth) {
		
		mapUrlToBreadcrumb.put(breadcrumb.getUrl(), breadcrumb);
		index.add(breadcrumb);
		breadcrumb.setDepth(depth);
		
		List<Breadcrumb> children = breadcrumb.getChildren();
		for (Breadcrumb child : children) {
			mapUrlToParent.put(child.getUrl(), breadcrumb);
			fillMapsAndList(child, depth + 1);
		}
	}
	
	public Breadcrumb getRoot() {
		return root;
	}
	
	public List<Breadcrumb> listParentCrumbsForUrl(String url) {
		
		List<Breadcrumb> list = new ArrayList<Breadcrumb>();
		Breadcrumb breadcrumb = mapUrlToBreadcrumb.get(url);
		
		if (breadcrumb == null) {
			return list;
		}
		
		while (true) {
			breadcrumb = mapUrlToParent.get(breadcrumb.getUrl());
			if (breadcrumb == null) {
				break;
			}
			list.add(0, breadcrumb);
		}
		
		return list;
	}
	
	public String getTitleForUrl(String url) {
		Breadcrumb breadcrumb = mapUrlToBreadcrumb.get(url);
		return breadcrumb == null ? null : breadcrumb.getTitle();
	}
	
	public String getNameForUrl(String url) {
		Breadcrumb breadcrumb = mapUrlToBreadcrumb.get(url);
		return breadcrumb == null ? null : breadcrumb.getName();
	}

	public List<Breadcrumb> getIndex() {
		return index;
	}
}
