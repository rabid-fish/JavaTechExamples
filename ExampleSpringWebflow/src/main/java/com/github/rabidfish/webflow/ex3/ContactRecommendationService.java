package com.github.rabidfish.webflow.ex3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rabidfish.webflow.ex2.ContactComplex;
import com.github.rabidfish.webflow.ex2.ContactComplexService;

@Service
public class ContactRecommendationService {

	@Autowired
	ContactComplexService contactService;
	
	public ContactRecommendationCommand initializeContactRecommendationCommand(List<ContactComplex> contacts) {
		
		List<String> recommended = new ArrayList<>();
		recommended.add("Jane");
		recommended.add("Joe");
		
		ArrayList<String> selected = new ArrayList<>(recommended);
		
		ContactRecommendationCommand command = new ContactRecommendationCommand();
		command.setContacts(contacts);
		command.setRecommendedNames(recommended);
		command.setSelectedNames(selected);
		command.updateContactListsFromNameLists();
		
		return command;
	}
}
