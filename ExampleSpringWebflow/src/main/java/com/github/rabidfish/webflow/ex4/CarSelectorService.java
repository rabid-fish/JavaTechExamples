package com.github.rabidfish.webflow.ex4;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.rabidfish.webflow.ex2.ContactComplexService;

public class CarSelectorService {

	@Autowired
	ContactComplexService contactService;
	@Autowired
	CarService carService;
	
	public CarSelectorCommand initializeCarSelectorCommand() {
		return new CarSelectorCommand();
	}

}
