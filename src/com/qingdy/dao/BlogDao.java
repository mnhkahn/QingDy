package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Blog;

public interface BlogDao extends DAO {

	public Blog getBlog(Long blogId);
	public void saveBlog(Blog blog);
	public void removeBlog(Long blogId);
	public List getBlogs();
}
