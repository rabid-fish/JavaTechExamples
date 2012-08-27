package com.github.rabid_fish.content;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/content")
public class ContentController {

	@Autowired
	ContentRepository repository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {

		Iterable<Content> list = repository.findAll();
		model.addAttribute("list", list);

		if (!list.iterator().hasNext()) {
			model.addAttribute("message", "No content items to list at this time");
		}
		
		return "contentList";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute(new Content());
		return "contentEdit";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createSave(@Valid Content content, BindingResult result) {

		if (result.hasErrors()) {
			return "contentEdit";
		}

		content.setId(null); // ensure this is not provided
		repository.save(content);

		return "redirect:list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("id") Long id) {
		Content content = repository.findOne(id);
		model.addAttribute(content);

		return "contentEdit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateSave(@Valid Content content, BindingResult result) {

		if (result.hasErrors()) {
			return "contentEdit";
		}

		repository.save(content);
		
		return "redirect:list";
	}

}
