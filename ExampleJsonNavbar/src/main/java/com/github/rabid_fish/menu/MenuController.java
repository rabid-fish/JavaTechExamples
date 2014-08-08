package com.github.rabid_fish.menu;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	MenuControllerData data = new MenuControllerData(new MenuHelper("/menu.json"));

	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap model) {

		data.exampleMethod(this, request, model);

		return "menu";
	}
}
