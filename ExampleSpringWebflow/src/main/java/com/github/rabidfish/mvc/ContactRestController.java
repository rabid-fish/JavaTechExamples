	package com.github.rabidfish.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.rabidfish.webflow.ex1.ContactSimple;
import com.github.rabidfish.webflow.ex1.ContactSimpleService;

@RestController
public class ContactRestController {

	@Autowired
	ContactSimpleService contactSimpleService;
	
	@RequestMapping(value = "/contact/initializeTestData", method = RequestMethod.GET)
	public boolean initializeTestData() {
		return contactSimpleService.initializeTestData();
	}

	@RequestMapping(value = "/contact/list", method = RequestMethod.GET)
	public List<ContactSimple> list() {
		return contactSimpleService.list();
	}
	
	@RequestMapping(value = "/contact/save", method = RequestMethod.POST)
	public void save(ContactSimple contact) {
		contactSimpleService.save(contact);
	}
}
