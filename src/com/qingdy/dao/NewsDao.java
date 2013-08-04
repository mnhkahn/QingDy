package com.qingdy.dao;

import java.util.List;

import com.qingdy.common.SQLParameters;
import com.qingdy.model.Grid;
import com.qingdy.model.Blog;

public interface NewsDao {

	public int addNews(Blog news);
	
	public List<Blog> getNewsList(int size, int page, String keyword);
	
	public Grid getNewsList(SQLParameters parameters);
	
	public Blog getNews(int nid);
	
	public int updateNews(Blog news);
	
	public int removeNews(int nid);
}
