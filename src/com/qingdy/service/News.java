package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.qingdy.common.CServlet;
import com.qingdy.dao.NewsDao;
import com.qingdy.dao.impl.NewsDaoImpl;
import com.qingdy.domain.QdMessage;
import com.qingdy.domain.QdNews;

/**
 * Servlet implementation class News
 */
@WebServlet("/News")
public class News extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private NewsDao newsDao = null;
	private QdNews news = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public News() {
        super();
        newsDao = new NewsDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		news = new QdNews();
		System.out.println(id);
		if (action.equals("manage")) {
			object = newsDao.getNewsList(parameters);
		}
		else if (id != null && !id.equals("")) {
			object = newsDao.getNews(Integer.parseInt(id));
		}
		else {
			
		}
		
		String json = JSONSerializer.toJSON(object , jsonConfig).toString();
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		news = new QdNews();
		initialize(request, response);
		
		if (action.equals("")) {
			newsDao.addNews(news);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		news = new QdNews();
		initialize(request, response);
		news.setNid(Integer.parseInt(id));
		if (action.equals("")) {
			newsDao.updateNews(news);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			newsDao.removeNews(Integer.parseInt(id));
		}
	}
	
	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdNews.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(news, json.get(json.keySet().toArray()[i]));
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
