package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.Action;
import com.qingdy.common.CServlet;
import com.qingdy.domain.QdBlog;
import com.qingdy.dao.BlogDao;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (size > 1) {
			if (id.length() > 0) {
				List<QdBlog> blogs = blogDao.getBlogByUser(id);
			}
			else {
				List<QdBlog> blogs = blogDao.getBlogList(size, page, keyword);
			}
		}
		else if (size == 1) {
			QdBlog blog = blogDao.getBlog(Integer.parseInt(id));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			blogDao.addBlog(blog);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
