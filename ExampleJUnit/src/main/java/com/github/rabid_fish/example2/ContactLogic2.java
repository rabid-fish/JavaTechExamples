package com.github.rabid_fish.example2;

import java.util.ArrayList;
import java.util.List;

import com.github.rabid_fish.model.Contact;

public class ContactLogic2 {

	public List<String> validateContact(Contact contact) {
		
		List<String> errors = new ArrayList<>();
		
		validateFirstName(contact, errors);
		validateLastName(contact, errors);
		validatePhoneNumber(contact, errors);
		
		return errors;
	}

	void validateFirstName(Contact contact, List<String> errors) {
		
		if (contact.getFirstName() == null || contact.getFirstName().trim().length() == 0) {
			errors.add("First name must not be empty");
			return;
		}
		
		if (contact.getFirstName().matches(".*[0-9]+.*")) {
			errors.add("First name must not contain numbers");
			return;
		}
	}
	
	void validateLastName(Contact contact, List<String> errors) {
		
		if (contact.getLastName() == null || contact.getLastName().trim().length() == 0) {
			errors.add("Last name must not be empty");
			return;
		}
		
		if (contact.getLastName().matches(".*[0-9]+.*")) {
			errors.add("Last name must not contain numbers");
			return;
		}
	}
	
	void validatePhoneNumber(Contact contact, List<String> errors) {
		
		if (contact.getPhoneNumber() == null) {
			return;
		}
		
		if (contact.getPhoneNumber().matches(".*[A-Za-z]+.*")) {
			errors.add("Phone number must not contain alpha values");
			return;
		}
		
		if (!contact.getPhoneNumber().matches("\\(\\d{3}\\) \\d{3}-\\d{4}")) {
			errors.add("Phone number must follow the pattern (123) 456-7890");
			return;
		}
		
	}

}
