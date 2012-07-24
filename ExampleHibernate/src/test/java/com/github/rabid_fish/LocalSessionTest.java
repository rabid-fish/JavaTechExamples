package com.github.rabid_fish;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring-localsession-context.xml")
public class LocalSessionTest {

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

}
