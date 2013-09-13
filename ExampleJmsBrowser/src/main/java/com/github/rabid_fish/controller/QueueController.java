package com.github.rabid_fish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.rabid_fish.config.ConfigQueue;
import com.github.rabid_fish.service.QueueService;

@Controller
@RequestMapping(value = "/queue")
public class QueueController {

	@Autowired
	QueueService queueService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(ModelMap model) {

		Iterable<ConfigQueue> list = queueService.getQueueConfigIterable();
		model.addAttribute("list", list);

		if (!list.iterator().hasNext()) {
			model.addAttribute("message", "No message items to list at this time");
		}
		
		return "queue/queueList";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getView(ModelMap model) {
		model.addAttribute("message", "Welcome to the queue view page");
		return "queue/queueView";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(ModelMap model) {
		return "redirect:../queue";
	}

}
