package com.github.rabid_fish;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring-context.xml")
public class UserRepositoryWithSpringTest {

	@Autowired
	UserRepository repository;

	@Test
	public void testSave() {

		User user = new User();
		user.setName("Dan");
		user = repository.save(user);

		assertEquals(user, repository.findOne(user.getId()));
	}
	
	@Test
	public void testFindByName() {
		
		assertTrue(repository.findByName("Dan") != null);
	}

}
