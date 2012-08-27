package com.github.rabid_fish.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GlobalUserSessionRepository {

	@PersistenceContext
	private EntityManager em;

	public GlobalUserSession create(HttpSession httpSession) {
		
		return create(httpSession.getId());
	}
	
	public GlobalUserSession create(String sessionId) {

		GlobalUserSession session = new GlobalUserSession();
		session.setSessionId(sessionId);
		em.persist(session);
		
		return session;
	}
	
	public GlobalUserSession update(GlobalUserSession session) {
		
		return em.merge(session);
	}
	
	public GlobalUserSession find(HttpSession httpSession) {
		
		return find(httpSession.getId());
	}
	
	public GlobalUserSession find(String sessionId) {

		return em.find(GlobalUserSession.class, sessionId);
	}

	public GlobalUserSession findOrCreate(HttpSession httpSession) {
		
		return findOrCreate(httpSession.getId());
	}
	
	public GlobalUserSession findOrCreate(String sessionId) {
		
		GlobalUserSession session = find(sessionId);
		if (session == null) {
			session = create(sessionId);
		}
		
		return session;
	}

	public List<GlobalUserSession> list() {

		TypedQuery<GlobalUserSession> query = em.createQuery("select a from GlobalUserSession a", GlobalUserSession.class);

		return query.getResultList();
	}

	public void delete(String sessionId) {

		GlobalUserSession session = find(sessionId);
		em.remove(session);
	}

}
