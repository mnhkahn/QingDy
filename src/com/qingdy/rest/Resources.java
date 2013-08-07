package com.qingdy.rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.qingdy.model.Blog;
import com.qingdy.model.Evaluate;
import com.qingdy.model.User;
import com.qingdy.service.FacadeManager;

@Path("/metadata")
public class Resources {
	private Log log = LogFactory.getLog(getClass());
	
	protected FacadeManager mgr;
	
	public void setMgr(FacadeManager mgr) {
		this.mgr = mgr;
	}
	
	@Path("/blog")
	@POST
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addBlog(Blog blog) {
		Timestamp postDate = new Timestamp(new Date().getTime());
		blog.setPostDate(postDate);
		
		User poster = new User();
		poster.setUsername("username");
		poster.setPassword("password");
		blog.setPoster(poster);
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/WEB-INF/applicationContext.xml");
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(blog);
		tx.commit();
		sess.close();
		return Response.status(Response.Status.CREATED).build();
	}
	
	@Path("/blog/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedBlog(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/WEB-INF/applicationContext.xml");
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Blog blog = (Blog)sess.get(Blog.class, new Long(1));
		sess.save(blog);
		tx.commit();
		sess.close();
		return Response.ok(blog).build();
	}
	
	@Path("/blog/{id}")
	@DELETE
	public Response deleteBlog(@PathParam("id") int id) {
		return null;
	}
	
	@Path("/blog/{id}")
	@PUT
	public Response updateBlog(@PathParam("id") int id, Blog blog) {
		
		return null;
	}
	
	@Path("/blog/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlog(@PathParam("id") Long id) {
		Blog blog = null;
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/WEB-INF/applicationContext.xml");
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		List l = sess.createCriteria(Blog.class).add(Restrictions.ge("id", id)).list();
		Iterator iterator = l.iterator();
		
		if (iterator.hasNext()) {
			blog = (Blog)iterator.next();
			System.out.println("$$$$$$$$$$$$$$$$$$$$$" + blog.getCategory() + blog.getContent());
		}
		tx.commit();
		sess.close();
		return Response.ok().build();
	}
	
	@Path("/user")
	@POST
	public Response addUser(User user) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/WEB-INF/applicationContext.xml");
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(user);
		tx.commit();
		sess.close();

		return Response.ok().build();
	}
	
	@Path("/evaluate")
	@POST
	public Response addEvaluate(Evaluate evaluate) {
		Timestamp postDate = new Timestamp(new Date().getTime());
		evaluate.setPostDate(postDate);
		
		User poster = new User();
		poster.setUsername("username");
		poster.setPassword("password");
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/WEB-INF/applicationContext.xml");
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();

		List l = sess.createCriteria(Blog.class).add(Restrictions.ge("id", new Long(1))).list();
		Iterator iterator = l.iterator();
		
		Blog blog = null;
		if (iterator.hasNext()) {
			blog = (Blog)iterator.next();
			System.out.println("$$$$$$$$$$$$$$$$$$$$$" + blog.getCategory() + blog.getContent());
		}
		System.out.println("***************8");
		evaluate.setBlog(blog);
		evaluate.setPoster(poster);
		sess.save(evaluate);
		tx.commit();
		sess.close();

		return Response.ok().build();
	}
}
