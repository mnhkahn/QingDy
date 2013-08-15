package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.NewsDao;
import com.qingdy.model.Blog;
import com.qingdy.model.News;

@Service("newsDao")
public class NewsDaoHibernate extends BaseDaoHibernate implements NewsDao {

	@Override
	public void saveNews(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<News> getNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News getNews(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeNews(Long id) {
		// TODO Auto-generated method stub
		
	}

}
