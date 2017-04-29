package com.github.rabidfish.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

	@RequestMapping("/contacts")
	public String contacts() {
		return "WEB-INF/mvc/contacts";
	}
}
