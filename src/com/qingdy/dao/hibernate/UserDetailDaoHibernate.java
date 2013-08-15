package com.qingdy.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.dao.UserDetailDao;
import com.qingdy.model.UserDetail;

@Service("userDetailDao")
public class UserDetailDaoHibernate extends BaseDaoHibernate implements UserDetailDao {

	@Override
	public List getObjects(Class cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Class cls, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveObject(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeObject(Class cls, Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUserDetail(UserDetail userDetail) {
		getHibernateTemplate().save(userDetail);
	}

	@Override
	public UserDetail getUserDetail(String username) {
		UserDetail userDetail = getHibernateTemplate().get(UserDetail.class, username);
		if (userDetail == null) {
			throw new ObjectRetrievalFailureException(UserDetail.class, username);
		}
		return userDetail;
	}

	@Override
	public void updateUserDetail(UserDetail userDetail) {
		getHibernateTemplate().update(userDetail);
	}

	@Override
	public List<UserDetail> getUsersDetail() {
		List<UserDetail> usersDetail = getHibernateTemplate().find("from UserDetail");
		return usersDetail;
	}

}
