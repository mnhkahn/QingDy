package com.qingdy.dao;

import com.qingdy.domain.QdMember;

public interface UserDao {

	public void addUser(QdMember user);
	
	public QdMember getUser(String username);
	
	public void updateUser(QdMember user);
	
	public int getUid(String username);
}
