package com.github.rabid_fish;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring-context.xml")
public class UserDaoImplTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public void testSave() {

		Session session = sessionFactory.openSession();
		
		User user = new User();
		user.setName("Dan");
		session.save(user);

		session.close();
	}
	
//	Wish I could autowire the Dao...
//	
//	@Autowired
//	UserDaoImpl repository;
//
//	@Test
//	public void testSave() {
//
//		User user = new User();
//		user.setName("Dan");
//		repository.save(user);
//
//		assertEquals(user, repository.findOne(user.getId()));
//	}
//	
//	@Test
//	public void testFindByName() {
//		
//		assertTrue(repository.findByName("Dan") != null);
//	}
	
}
