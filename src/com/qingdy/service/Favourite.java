package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.FavouriteDao;
import com.qingdy.dao.impl.FavouriteDaoImpl;
import com.qingdy.domain.QdFavourite;

/**
 * Servlet implementation class Favourite
 */
@WebServlet("/Favourite")
public class Favourite extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private FavouriteDao favouriteDao = null;
	private QdFavourite favourite = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Favourite() {
        super();
        favouriteDao = new FavouriteDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (size >= 1) {
			list = favouriteDao.getFavouriteList(size, page);
		}
		
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		favourite = new QdFavourite();
		initialize(request, response);
		if (action.equals("")) {
			favouriteDao.addFavourite(favourite);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		favourite = new QdFavourite();
		initialize(request, response);
		favourite.setFid(Integer.parseInt(id));
		if (action.equals("")) {
			favouriteDao.updateFavourite(favourite);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			favouriteDao.removeFavourite(Integer.parseInt(id));
		}
	}

	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdFavourite.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(favourite, json.get(json.keySet().toArray()[i]));
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
