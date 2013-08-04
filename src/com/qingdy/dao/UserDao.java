package com.qingdy.dao;

import com.qingdy.model.QdMember;

public interface UserDao {

	public int addUser(QdMember user);
	
	public QdMember getUser(String username, String password);
	
	public int updateUser(QdMember user);
	
	public int getUid(String username);
	
//	public List<QdPost> getPosts(String username);
}
