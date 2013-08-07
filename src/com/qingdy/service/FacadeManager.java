package com.qingdy.service;

import com.qingdy.model.Evaluate;
import com.qingdy.model.User;
import com.qingdy.model.Blog;

public interface FacadeManager {

	public Blog getBlog(Long id);
	
	public void saveBlog(Blog blog);
	
	public void removeBlog(String id);
	
	public Evaluate getEvaluate(String id);
	
	public void saveEvaluate(Evaluate evaluate);
	
	public void removeEvaluate(String id);
	
	public User getUser(String username);
	
	public void saveUser(User user);
	
	public void removeUser(String username);
	
	public boolean validateUser(User user);
}
