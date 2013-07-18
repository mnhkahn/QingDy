package com.qingdy.dao;

import java.util.List;

import com.qingdy.common.SQLParameters;
import com.qingdy.domain.Grid;
import com.qingdy.domain.QdNews;

public interface NewsDao {

	public int addNews(QdNews news);
	
	public List<QdNews> getNewsList(int size, int page, String keyword);
	
	public Grid getNewsList(SQLParameters parameters);
	
	public QdNews getNews(int nid);
	
	public int updateNews(QdNews news);
	
	public int removeNews(int nid);
}
