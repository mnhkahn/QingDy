package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.qingdy.common.CServlet;
import com.qingdy.dao.NewsclassesDao;
import com.qingdy.dao.impl.NewsclassesDaoImpl;

/**
 * Servlet implementation class NewsClasses
 */
@WebServlet("/NewsClasses")
public class NewsClasses extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private NewsclassesDao newsclassesDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsClasses() {
        super();
        newsclassesDao = new NewsclassesDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			object = newsclassesDao.getNewsclasses();
		}
		
		String json = JSONSerializer.toJSON(object , jsonConfig).toString();
		response.getWriter().write(json);
	}

}
