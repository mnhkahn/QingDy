package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

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
		Blog blog = (Blog)getHibernateTemplate().get(Blog.class, blogId);
		System.out.println(blog.getPostDate() + "^^^" + blog.getId());
		getHibernateTemplate().delete(blog);
	}

	@Override
	public List getBlogs() {
		return getHibernateTemplate().find("from Blog");
	}

}
