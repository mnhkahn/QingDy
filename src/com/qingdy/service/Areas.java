package com.qingdy.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.AreaDao;
import com.qingdy.dao.impl.AreaDaoImpl;

/**
 * Servlet implementation class Areas
 */
@WebServlet("/Areas")
public class Areas extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private AreaDao areaDao = null;
	private com.qingdy.domain.Areas areas = new com.qingdy.domain.Areas();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Areas() {
        super();
        areaDao = new AreaDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list = new LinkedList<>();
		if (action.equals("province")) {
			list = areaDao.getProvince();
		}
		else if (action.equals("city")) {
			id = request.getParameter("province");
			list = areaDao.getCity(Integer.parseInt(id));
		}
		else if (action.equals("county")) {
			id = request.getParameter("city");
			list = areaDao.getCounty(Integer.parseInt(id));
		}
		
		super.doGet(request, response);
	}

}
