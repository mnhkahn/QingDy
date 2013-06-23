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
	private QdMall mall = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mall() {
        super();
        mallDao = new MallDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list.clear();
		
		// Get mall list
		if (this.size > 1) {
			list = mallDao.getMallList(this.size, this.page, this.keyword);
		}
		// Get one mall
		else if (this.size == 1) {
			String username = request.getParameter("username");
			if (username != null) {
				list.add(mallDao.getMallByUser(username));
			}
			else if (!id.equals("-1")) {
				list.add(mallDao.getMall(Integer.parseInt(this.id)));
			}
		}
		
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create one mall
		if (this.action.equals("")) {
			try {
				mall = (QdMall)initialize(QdMall.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		try {
			mall = (QdMall)initialize(QdMall.class, request, response);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mall.setMid(Integer.parseInt(id));
		if (this.action.equals("")) {
			mallDao.updateMall(mall);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.action.equals("")) {
			mallDao.removeMall(Integer.parseInt(this.id));
		}
	}

}
