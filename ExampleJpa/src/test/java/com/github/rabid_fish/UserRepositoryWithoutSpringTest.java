package com.github.rabid_fish;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

/**
 * Written with help from Spring Data - JPA examples.
 */
public class UserRepositoryWithoutSpringTest {

	private UserRepository userRepository;
	private EntityManager em;

	@Before
	public void setUp() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("exampleJpaWithoutSpring");
		em = factory.createEntityManager();

		userRepository = new JpaRepositoryFactory(em).getRepository(UserRepository.class);

		em.getTransaction().begin();
	}

	@After
	public void tearDown() {
		em.getTransaction().rollback();
	}

	@Test
	public void savingUsers() {

		User user = new User();
		user.setName("Dan");

		user = userRepository.save(user);

		assertEquals(user, userRepository.findOne(user.getId()));
	}
}
