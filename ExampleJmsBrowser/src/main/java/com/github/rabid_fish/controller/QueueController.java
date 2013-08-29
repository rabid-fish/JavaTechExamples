package com.github.rabid_fish.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/queue")
public class QueueController {

	@RequestMapping(method = RequestMethod.GET)
	public String getHello(ModelMap model) {
		model.addAttribute("message", "Welcome to the queue default page");
		return "queue/queueList";
	}

//	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
//	public String getHelloAjax(ModelMap model) {
//		model.addAttribute("message", "Hello from Spring MVC via AJAX!");
//		model.addAttribute("ajax", "true");
//		return "hello/helloAjax";
//	}
//	
//	@RequestMapping(value = "/getTime", method = RequestMethod.GET)
//	public @ResponseBody Map<String, ? extends Object> getTime() {
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:ss:mm");
//		String time = format.format(new Date());
//		return Collections.singletonMap("theTime", time);
//	}

}
