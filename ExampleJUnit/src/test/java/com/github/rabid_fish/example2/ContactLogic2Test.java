package com.github.rabid_fish.example2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.github.rabid_fish.model.Contact;

public class ContactLogic2Test {
	
	@Test
	public void validateFirstName() {
		
	}
	
	@Test
	public void validateFirstName_Shorter() {
		
	}
	
	@Test
	public void validateFirstName_Shortest() {
		
		assertContactLogicValidate("Jane",  "Doe",  "(515) 555-1212", new String[]{ });
	}
	
	void assertContactLogicValidate(String firstName, String lastName, String phoneNumber, String[] expectedErrorsArray) {
		
		List<String> expectedErrors = Arrays.asList(expectedErrorsArray);
		Contact contact = new Contact(null, firstName, lastName, phoneNumber);
		ContactLogic2 logic = new ContactLogic2();
		
		List<String> resultingErrors = logic.validateContact(contact);
		
		if (expectedErrors.size() == 0 && resultingErrors.size() != 0) {
			assertTrue("Expected no return errors but got one or more", false);
		}
		
		List<String> missingInExpected = findMissingErrors(expectedErrors, resultingErrors);
		List<String> missingInResult = findMissingErrors(resultingErrors, expectedErrors);
		
		if (missingInExpected.isEmpty() && missingInResult.isEmpty()) {
			return;
		}
		
		StringBuffer message = buildAssertionMessage(missingInExpected, missingInResult);
		
		assertTrue(message.toString(), false);
	}

	StringBuffer buildAssertionMessage(List<String> missingInExpected, List<String> missingInResult) {
		
		StringBuffer message = new StringBuffer();
		
		for (String error : missingInExpected) {
			message.append("Expected errors does not contain '" + error + "', but test results do\n");
		}
		
		for (String error : missingInResult) {
			message.append("Test result errors does not contain '" + error + "', but expected results do\n");
		}
		
		return message;
	}

	List<String> findMissingErrors(List<String> listLoopOver, List<String> listContains) {
		
		List<String> missing = new ArrayList<>();
		
		Set<String> setContains = new HashSet<>(listContains);
		for (String value : listLoopOver) {
			if (!setContains.contains(value)) {
				missing.add(value);
			}
		}
		
		return missing;
	}
}
