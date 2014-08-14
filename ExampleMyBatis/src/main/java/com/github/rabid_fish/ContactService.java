package com.github.rabid_fish;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class ContactService {

	public void createContactTable() {
		
		SqlSession sqlSession = DatabaseUtil.getSqlSessionFactory().openSession();
		
		try {
			ContactMapper contactMapper = sqlSession.getMapper(ContactMapper.class);
			contactMapper.createContactTable();
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertContact(Contact contact) {

		SqlSession sqlSession = DatabaseUtil.getSqlSessionFactory().openSession();

		try {
			ContactMapper contactMapper = sqlSession.getMapper(ContactMapper.class);
			contactMapper.insertContact(contact);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public List<Contact> getContacts() {
		
		SqlSession sqlSession = DatabaseUtil.getSqlSessionFactory()
				.openSession();
		try {
			ContactMapper contactMapper = sqlSession.getMapper(ContactMapper.class);
			return contactMapper.getContacts();
		} finally {
			sqlSession.close();
		}
	}
}
