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
import com.qingdy.model.Mall;
import com.qingdy.model.UserDetail;

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

	@Override
	public List<Blog> getBlogs(String username) {
		UserDetail poster = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Blog.class).add(Restrictions.eq("poster", poster)));
	}

}
