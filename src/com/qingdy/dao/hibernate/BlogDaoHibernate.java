package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.BlogDao;
import com.qingdy.model.Blog;

@Service("blogDao")
public class BlogDaoHibernate extends BaseDaoHibernate implements BlogDao {

	@Override
	public Blog getBlog(Long blogId) {
		Blog blog = (Blog)getHibernateTemplate().get(Blog.class, blogId);
		if (blog == null) {
			throw new ObjectRetrievalFailureException(Blog.class, blogId);
		}
		return blog;
	}

	@Override
	public void saveBlog(Blog blog) {
		getHibernateTemplate().saveOrUpdate(blog);
	}

	@Override
	public void removeBlog(Long blogId) {
		Blog blog = getBlog(blogId);
		getHibernateTemplate().delete(blog);
	}

	@Override
	public List<Blog> getBlogs(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Blog.class, field, value, operator, sidx, sord, verify), (page - 1) * size, size);
	}

	@Override
	public void verifyBlog(Long id, boolean verify) {
		Blog blog = getBlog(id);
		if (verify) {
			blog.setVerify(1);
		}
		else {
			blog.setVerify(0);
		}
		saveBlog(blog);
	}

}
