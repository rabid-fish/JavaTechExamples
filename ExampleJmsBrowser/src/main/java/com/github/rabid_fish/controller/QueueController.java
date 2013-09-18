package com.github.rabid_fish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.model.QueueData;
import com.github.rabid_fish.service.QueueService;

@Controller
@RequestMapping(value = "/queue")
public class QueueController {

	@Autowired
	QueueService queueService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(ModelMap model) {

		Iterable<QueueData> list = queueService.getQueueDataIterable();
		model.addAttribute("list", list);

		if (!list.iterator().hasNext()) {
			model.addAttribute("message", "No message items to list at this time");
		}
		
		return "queue/queueList";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getView(
			@RequestParam("queueName") String queueName, 
			@RequestParam("messageId") String messageId,
			ModelMap model) {
		
		model.addAttribute("message", "Welcome to the queue view page");
		
		MessageData messageData = queueService.getDetailedMessageDataForMessageId(queueName, messageId);
		model.addAttribute("messageData", messageData);
		
		return "queue/queueView";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(ModelMap model) {
		return "redirect:../queue";
	}

}
