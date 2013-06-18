package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.MallDao;
import com.qingdy.dao.impl.MallDaoImpl;
import com.qingdy.domain.QdMall;

/**
 * Servlet implementation class Mall
 */
@WebServlet("/Mall")
public class Mall extends CServlet {
	private static final long serialVersionUID = 1L;
	
	private MallDao mallDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mallDao = new MallDaoImpl();
		
		// Get mall list
		if (this.size > 1) {
			List<QdMall> malls = mallDao.getMallList(this.size, this.page, this.keyword);
		}
		// Get one mall
		else if (this.size == 1) {
			QdMall mall = mallDao.getMall(Integer.parseInt(this.id));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mallDao = new MallDaoImpl();
		
		QdMall mall = new QdMall();
		
		// Create one mall
		if (this.action.equals("")) {
			mallDao.addMall(mall);
		}
		else if (this.action.equals("positive")) {
			mallDao.verifyMall(Integer.parseInt(this.id), 1);
		}
		else if (this.action.equals("negative")) {
			mallDao.verifyMall(Integer.parseInt(this.id), 0);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mallDao = new MallDaoImpl();
		
		QdMall mall = new QdMall();
		
		if (this.action.equals("")) {
			mallDao.updateMall(mall);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
mallDao = new MallDaoImpl();
		
		QdMall mall = new QdMall();
		
		if (this.action.equals("")) {
			mallDao.removeMall(Integer.parseInt(this.id));
		}
	}

}
