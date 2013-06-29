package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdBlog;

public interface BlogDao {

	public int addBlog(QdBlog blog);
	
	public List<QdBlog> getBlogList(int size, int page, String keyword);
	
	public QdBlog getBlog(int bid);
	
	public List<QdBlog> getBlogByUser(String username, int size, int page);
	
	public int updateBlog(QdBlog blog);
	
	public int removeBlog(int bid);
}
