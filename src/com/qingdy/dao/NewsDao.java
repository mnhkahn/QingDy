package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.News;

public interface NewsDao {

	public void saveNews(News news);
	
	public List<News> getNews(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public News getNews(Long id);

	public void removeNews(Long id);
}
