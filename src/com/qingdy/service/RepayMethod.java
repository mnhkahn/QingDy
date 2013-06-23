package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.RepaymethodDao;
import com.qingdy.dao.impl.RepaymethodDaoImpl;

/**
 * Servlet implementation class RepayMethod
 */
@WebServlet("/RepayMethod")
public class RepayMethod extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private RepaymethodDao repaymethodDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepayMethod() {
        super();
        repaymethodDao = new RepaymethodDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = repaymethodDao.getRepaymethods();
		}
		
		super.doGet(request, response);
	}

}
