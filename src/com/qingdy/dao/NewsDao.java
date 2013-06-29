package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdNews;

public interface NewsDao {

	public int addNews(QdNews news);
	
	public List<QdNews> getNewsList(int size, int page, String keyword);
	
	public QdNews getNews(int nid);
	
	public int updateNews(QdNews news);
	
	public int removeNews(int nid);
}
