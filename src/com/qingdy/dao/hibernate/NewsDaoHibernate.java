package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.NewsDao;
import com.qingdy.model.Blog;
import com.qingdy.model.News;

@Service("newsDao")
public class NewsDaoHibernate extends BaseDaoHibernate implements NewsDao {

	@Override
	public void saveNews(News news) {
		getHibernateTemplate().saveOrUpdate(news);
	}

	@Override
	public List<News> getNews(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(News.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}

	@Override
	public News getNews(Long id) {
		News news = getHibernateTemplate().get(News.class, id);
		if (news == null) {
			throw new ObjectRetrievalFailureException(News.class, id);
		}
		return news;
	}

	@Override
	public void removeNews(Long id) {
		News news = getNews(id);
		getHibernateTemplate().delete(news);
	}

}
