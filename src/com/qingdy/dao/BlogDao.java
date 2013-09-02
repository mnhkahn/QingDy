package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Blog;

public interface BlogDao extends DAO {

	public Blog getBlog(Long blogId);
	public void saveBlog(Blog blog);
	public void removeBlog(Long blogId);
	public List<Blog> getBlogs(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	public void verifyBlog(Long id, boolean verify);
}
