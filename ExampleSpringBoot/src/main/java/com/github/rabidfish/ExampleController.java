package com.github.rabidfish;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
}
