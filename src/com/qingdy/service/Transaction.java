package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.common.Constant;
import com.qingdy.dao.TransactionDao;
import com.qingdy.domain.QdTransaction;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private TransactionDao transactionDao = null;
	private QdTransaction transaction = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (size > 1) {
			List<QdTransaction> transactions = transactionDao.geTransactionList(size, page);
		}
		else if (size == 1) {
			QdTransaction transaction = transactionDao.getTransaction(Integer.parseInt(id));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			transactionDao.addTransaction(transaction);
		}
		else if (action.equals("positive")) {
			transactionDao.verifyTransaction(Integer.parseInt(id), Constant.POSITIVE);
		}
		else if (action.equals("negative")) {
			transactionDao.verifyTransaction(Integer.parseInt(id), Constant.NEGATIVE);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			transactionDao.updateTransaction(transaction);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			transactionDao.removeTransaction(Integer.parseInt(id));
		}
	}

}
