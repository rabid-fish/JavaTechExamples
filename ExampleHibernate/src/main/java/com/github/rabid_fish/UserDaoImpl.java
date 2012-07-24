package com.github.rabid_fish;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Written with help from:
 * http://www.baeldung.com/2011/12/02/the-persistence-layer
 * -with-spring-3-1-and-hibernate/
 */

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(User entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public User findOne(Long id) {
		return (User) getCurrentSession().get(User.class, id);
	}

	@Override
	public User findByName(String name) {
		return (User) getCurrentSession().createQuery( "from " + User.class.getName() ).uniqueResult();
   }

	protected final Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

}
