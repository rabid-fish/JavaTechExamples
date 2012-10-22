package com.github.rabid_fish.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	MenuHelper helper = new MenuHelper("/menu.json");
	
	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap model) {

		String url = request.getRequestURI();
		List<MenuItem> list = helper.listParentMenuItemsForUrl(url);
		model.addAttribute("list", list);

		model.addAttribute("title", helper.getTitleForUrl(url));
		model.addAttribute("name", helper.getNameForUrl(url));
		model.addAttribute("menuItems", helper.getIndex());

		return "menu";
	}
}
