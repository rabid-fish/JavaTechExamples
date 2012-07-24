package com.github.rabid_fish;


public interface UserDao {

	public void save(User entity);
	public User findOne(Long id);
	public User findByName(String name);
	
}
