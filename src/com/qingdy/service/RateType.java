package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.RatetypeDao;
import com.qingdy.dao.impl.RatetypeDaoImpl;

/**
 * Servlet implementation class RateType
 */
@WebServlet("/RateType")
public class RateType extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private RatetypeDao ratetypeDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateType() {
        super();
        ratetypeDao = new RatetypeDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = ratetypeDao.getRatetypes();
		}
		
		super.doGet(request, response);
	}

}
