package com.github.rabidfish.webflow.ex4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabidfish.webflow.ex2.ContactComplex;
import com.github.rabidfish.webflow.ex2.ContactComplexService;

@Service
public class CarSelectorService {

	@Autowired
	ContactComplexService contactService;
	@Autowired
	CarService carService;
	
	public CarSelectorCommand initializeCarSelectorCommand() {
		
		List<ContactComplex> contacts = contactService.list();
		
		Map<ContactComplex, List<Car>> mapContactToCars = new HashMap<>();
		Map<Long, List<Long>> mapContactIdToCarIds = new HashMap<>();
		
		for (ContactComplex contact : contacts) {
			mapContactToCars.put(contact, new ArrayList<>());
			mapContactIdToCarIds.put(contact.getId(), new ArrayList<>());
		}
		
		CarSelectorCommand command = new CarSelectorCommand();
		command.setContacts(contacts);
		command.setCars(carService.list());
		command.setMapContactIdToCarIds(mapContactIdToCarIds);
		
		return command;
	}

}
