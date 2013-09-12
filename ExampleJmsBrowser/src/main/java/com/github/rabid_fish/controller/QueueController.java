package com.github.rabid_fish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/queue")
public class QueueController {

	@RequestMapping(method = RequestMethod.GET)
	public String getList(ModelMap model) {
		model.addAttribute("message", "Welcome to the queue default page");
		return "queue/queueList";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getView(ModelMap model) {
		model.addAttribute("message", "Welcome to the queue view page");
		return "queue/queueView";
	}

}
