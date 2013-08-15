package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.UserDetail;

public interface UserDetailDao extends DAO {

	public void saveUserDetail(UserDetail userDetail);
	
	public void updateUserDetail(UserDetail userDetail);
	
	public UserDetail getUserDetail(String username);
	
	public List<UserDetail> getUsersDetail();
}
