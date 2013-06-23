package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.UsesofloanDao;
import com.qingdy.dao.impl.UsesofloanDaoImpl;

/**
 * Servlet implementation class UsesOfLoan
 */
@WebServlet("/UsesOfLoan")
public class UsesOfLoan extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private UsesofloanDao usesofloanDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsesOfLoan() {
        super();
        usesofloanDao = new UsesofloanDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = usesofloanDao.getUsesofloans();
		}
		
		super.doGet(request, response);
	}

}
