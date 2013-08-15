package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.News;

public interface NewsDao {

	public void saveNews(News news);
	
	public List<News> getNews();
	
	public News getNews(Long id);

	public void removeNews(Long id);
}
