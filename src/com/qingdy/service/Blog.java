package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.domain.QdBlog;
import com.qingdy.dao.BlogDao;
import com.qingdy.dao.impl.BlogDaoImpl;

/**
 * Servlet implementation class Blog
 */
@WebServlet("/Blog")
public class Blog extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private BlogDao blogDao = null;
	private QdBlog blog = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Blog() {
        super();
        blogDao = new BlogDaoImpl();
        blog = new QdBlog();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		list = new LinkedList<>();
		if (size > 1) {
			if (username != null) {
				list = blogDao.getBlogByUser(username, size, page);
			}
			else {
				list = blogDao.getBlogList(size, page, keyword);
			}
		}
		else if (size == 1) {
			list.add(blogDao.getBlog(Integer.parseInt(id)));
		}
		super.doGet(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialize(request, response);
		if (action.equals("")) {
			blogDao.addBlog(blog);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialize(request, response);
		blog.setBid(Integer.parseInt(id));
		if (action.equals("")) {
			blogDao.updateBlog(blog);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			blogDao.removeBlog(Integer.parseInt(id));
		}
	}
	
	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdBlog.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(blog, json.get(json.keySet().toArray()[i]));
				}
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
