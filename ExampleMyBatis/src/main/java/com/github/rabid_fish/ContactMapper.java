package com.github.rabid_fish;

import java.util.List;

public interface ContactMapper {

	public void createContactTable();
	public void insertContact(Contact contact);
	public List<Contact> getContacts();

}