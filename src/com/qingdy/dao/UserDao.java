package com.qingdy.dao;

import com.qingdy.model.User;

public interface UserDao extends DAO {

	public User getUser(String username);
	public void saveUser(User user);
	public void removeUser(String username);
	
}
