package com.github.rabid_fish;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao {

	public void save(User entity);
	public User findOne(Long id);
	public User findByName(String name);
	
}
