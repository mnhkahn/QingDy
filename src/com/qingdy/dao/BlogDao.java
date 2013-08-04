package com.qingdy.dao;

import java.util.List;

import com.qingdy.common.SQLParameters;
import com.qingdy.model.Grid;
import com.qingdy.model.QdBlog;

public interface BlogDao {

	public int addBlog(QdBlog blog);
	
	public Grid getBlogList(SQLParameters parameters);
	
	public QdBlog getBlog(int bid);
	
	public List<QdBlog> getBlogByUser(String username, int size, int page);
	
	public int updateBlog(QdBlog blog);
	
	public int removeBlog(int bid);
}
