package com.qingdy.dao.hibernate;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.dao.UserDao;
import com.qingdy.model.User;

@Service("userDao")
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
		getHibernateTemplate().save(user);
	}
	
	// User.hbm.xml doesn't config the generator of the id, so it will call update by default
	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void removeUser(String username) {
		getHibernateTemplate().delete(username);
	}

	@Override
	public boolean isUserExists(String username) {
		if (getHibernateTemplate().get(User.class, username) != null)
			return false;
		else
			return true;
	}

}
