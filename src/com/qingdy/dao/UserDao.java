package com.qingdy.dao;

import com.qingdy.domain.QdMember;

public interface UserDao {

	public int addUser(QdMember user);
	
	public QdMember getUser(String username);
	
	public int updateUser(QdMember user);
	
	public int getUid(String username);
	
//	public List<QdPost> getPosts(String username);
}
