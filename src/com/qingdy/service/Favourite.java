package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.FavouriteDao;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (size >= 1) {
			List<QdFavourite> favourites = favouriteDao.getFavouriteList(size, page);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			favouriteDao.addFavourite(favourite);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
