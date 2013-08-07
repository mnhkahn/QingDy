package com.qingdy.dao.hibernate;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.qingdy.dao.UserDao;
import com.qingdy.model.User;

public class UserDaoHibernate extends BaseDaoHibernate implements UserDao {

	@Override
	public User getUser(String username) {
		User user = (User)getHibernateTemplate().get(User.class, username);
		if (user == null) {
			throw new ObjectRetrievalFailureException(User.class, username);
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public void removeUser(String username) {
		getHibernateTemplate().delete(username);
	}

}
