package com.qingdy.service.impl;

import com.qingdy.dao.BlogDao;
import com.qingdy.dao.EvaluateDao;
import com.qingdy.dao.UserDao;
import com.qingdy.model.Evaluate;
import com.qingdy.model.User;
import com.qingdy.model.Blog;
import com.qingdy.service.BaseManager;
import com.qingdy.service.FacadeManager;

public class FacadeManagerImpl extends BaseManager implements FacadeManager {
	
	private BlogDao blogDao;
	private EvaluateDao evaluateDao;
	private UserDao userDao;

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public void setEvaluateDao(EvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Blog getBlog(Long id) {
		return blogDao.getBlog(id);
	}

	@Override
	public void saveBlog(Blog blog) {
		blogDao.saveBlog(blog);
	}

	@Override
	public void removeBlog(String id) {
		blogDao.removeBlog(Long.valueOf(id));
	}

	@Override
	public Evaluate getEvaluate(String id) {
		return evaluateDao.getEvaluate(Long.valueOf(id));
	}

	@Override
	public void saveEvaluate(Evaluate evaluate) {
		evaluateDao.saveBlogEvaluate(evaluate);
	}

	@Override
	public void removeEvaluate(String id) {
		evaluateDao.removeEvaluate(Long.valueOf(id));
	}

	@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void removeUser(String username) {
		userDao.removeUser(username);
	}
	
	@Override
	public boolean validateUser(User aUser) {
		User user = getUser(aUser.getUsername());
		if (user != null && user.getPassword().equals(aUser.getPassword()))
			return true;
		else
			return false;
	}

}
