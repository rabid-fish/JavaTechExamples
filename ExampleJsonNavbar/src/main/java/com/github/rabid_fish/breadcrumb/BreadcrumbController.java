package com.github.rabid_fish.breadcrumb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/breadcrumb")
public class BreadcrumbController {

	BreadcrumbHelper helper = new BreadcrumbHelper("/breadcrumb.json");
	
	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap model) {

		String url = request.getRequestURI();
		List<Breadcrumb> list = helper.listParentCrumbsForUrl(url);
		model.addAttribute("list", list);

		model.addAttribute("root", helper.getRoot());
		model.addAttribute("title", helper.getTitleForUrl(url));
		model.addAttribute("name", helper.getNameForUrl(url));
		model.addAttribute("index", helper.getIndex());

		return "breadcrumb";
	}
}
