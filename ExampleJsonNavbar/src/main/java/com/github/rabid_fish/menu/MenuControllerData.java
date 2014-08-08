package com.github.rabid_fish.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

public class MenuControllerData {
	public MenuHelper helper;

	public MenuControllerData(MenuHelper helper) {
		this.helper = helper;
	}

	void exampleMethod(MenuController menuController, HttpServletRequest request, ModelMap model) {
		String url = request.getRequestURI();
		List<MenuItem> list = helper.listParentMenuItemsForUrl(url);
		model.addAttribute("list", list);
	
		model.addAttribute("title", helper.getTitleForUrl(url));
		model.addAttribute("name", helper.getNameForUrl(url));
		model.addAttribute("menuItems", helper.getIndex());
	}
}