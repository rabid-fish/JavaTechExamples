package com.github.rabid_fish.controller;

import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.rabid_fish.model.MessageData;
import com.github.rabid_fish.model.QueueData;
import com.github.rabid_fish.service.QueueService;
import com.github.rabid_fish.util.CommonUtil;

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
			model.addAttribute("userMessage", "No message items to list at this time");
		}
		
		return "queue/queueList";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getView(
			@RequestParam("queueName") String queueName, 
			@RequestParam("messageId") String messageId,
			ModelMap model) throws TransformerException {
		
		MessageData messageData = queueService.getDetailedMessageDataForMessageId(queueName, messageId);
		model.addAttribute("message", messageData);
		model.addAttribute("queueName", queueName);
		model.addAttribute("messageId", messageId);
		
		CommonUtil.prettifyMessageBody(messageData);
		
		return "queue/queueView";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getSearch(
			@RequestParam("queueName") String queueName, 
			ModelMap model) {
		
		Iterable<MessageData> list = queueService.getMessageDataIterable(queueName);
		model.addAttribute("list", list);
		
		if (!list.iterator().hasNext()) {
			model.addAttribute("userMessage", "No message items to list at this time");
		}
		
		return "queue/queueList";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(
			@RequestParam("queueName") String queueName, 
			@RequestParam("messageId") String messageId,
			ModelMap model) {
		
		// Drop error message into session and show on list page 'errorMessage' div
		
		queueService.removeMessage(queueName, messageId);
		
		return "redirect:../queue";
	}

}
