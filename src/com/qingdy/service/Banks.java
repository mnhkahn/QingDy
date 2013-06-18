package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.domain.QdBanks;
import com.qingdy.dao.BankDao;

/**
 * Servlet implementation class Banks
 */
@WebServlet("/Banks")
public class Banks extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private BankDao bankDao;
	private QdBanks bank;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Banks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (size > 1) {
			List<QdBanks> banks = bankDao.getBankList(size, page, keyword);
		}
		else if (size == 1) {
			QdBanks bank = bankDao.getBank(Integer.parseInt(id));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			bankDao.addBank(bank);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			bankDao.updateBank(bank);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			bankDao.removeBank(Integer.parseInt(id));
		}
	}

}
