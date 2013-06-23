package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.LendTypeDao;
import com.qingdy.dao.impl.LendTypeDaoImpl;

/**
 * Servlet implementation class LendType
 */
@WebServlet("/LendType")
public class LendType extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private LendTypeDao lendTypeDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendType() {
        super();
        lendTypeDao = new LendTypeDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = lendTypeDao.getLendtype();
		}
		
		super.doGet(request, response);
	}

}
