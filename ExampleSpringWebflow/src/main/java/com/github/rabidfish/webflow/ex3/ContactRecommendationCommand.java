package com.github.rabidfish.webflow.ex3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.AssertTrue;

import com.github.rabidfish.webflow.ex2.ContactComplex;

public class ContactRecommendationCommand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ContactComplex> contacts;
	private List<String> recommendedNames;
	private List<ContactComplex> recommendedContacts;
	private List<String> selectedNames;
	private List<ContactComplex> selectedContacts;
	
	public ContactRecommendationCommand() {
		super();
	}
	
	public void updateContactListsFromNameLists() {
		recommendedContacts = getContactsMatchingNames(recommendedNames, getContacts());
		selectedContacts = getContactsMatchingNames(selectedNames, getContacts());
	}

	List<ContactComplex> getContactsMatchingNames(List<String> contactNames, List<ContactComplex> contacts) {
		
		List<ContactComplex> matches = new ArrayList<>();
		
		Map<String, ContactComplex> mapNamesToContact = contacts.stream().collect(Collectors.toMap(ContactComplex::getFirstName, Function.identity()));
		
		for (String contactName : contactNames) {
			ContactComplex contact = mapNamesToContact.get(contactName);
			if (contact == null) {
				throw new RuntimeException("Unable to locate contact");
			}
			matches.add(contact);
		}
		
		return matches;
	}
	
	@AssertTrue(message = "Required contacts are not selected")
	public boolean isContactsSelectedHasRequiredNames() {
		
		if (selectedNames == null) {
			return false;
		}
		
		updateContactListsFromNameLists();
		
		String[] requiredNames = { "Jane" };
		for (String required : requiredNames) {
			if (!selectedNames.contains(required)) {
				return false;
			}
		}
		
		return true;
	}


	public List<ContactComplex> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactComplex> contacts) {
		this.contacts = contacts;
	}
	public List<String> getRecommendedNames() {
		return recommendedNames;
	}
	public void setRecommendedNames(List<String> recommendedNames) {
		this.recommendedNames = recommendedNames;
	}
	public List<ContactComplex> getRecommendedContacts() {
		return recommendedContacts;
	}
	public void setRecommendedContacts(List<ContactComplex> recommendedContacts) {
		this.recommendedContacts = recommendedContacts;
	}
	public List<String> getSelectedNames() {
		return selectedNames;
	}
	public void setSelectedNames(List<String> selectedNames) {
		this.selectedNames = selectedNames;
	}
	public List<ContactComplex> getSelectedContacts() {
		return selectedContacts;
	}
	public void setSelectedContacts(List<ContactComplex> selectedContacts) {
		this.selectedContacts = selectedContacts;
	}
}
