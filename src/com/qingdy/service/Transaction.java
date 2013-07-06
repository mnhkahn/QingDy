package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.qingdy.common.CServlet;
import com.qingdy.common.Constant;
import com.qingdy.dao.TransactionDao;
import com.qingdy.dao.impl.TransactionDaoImpl;
import com.qingdy.domain.QdProduct;
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
        transactionDao = new TransactionDaoImpl();
        transaction = new QdTransaction();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		list = new LinkedList<>();
		if (action.equals("verify")) {
			if (username != null) {
//				list = transactionDao.getTransactionByUser(username, size, page);
			}
			else {
				object = transactionDao.geTransactionList(parameters);
				
				String json = JSONSerializer.toJSON(object , jsonConfig).toString();
				response.getWriter().write(json);
			}
		}
		else if (parameters.getSize() == 1) {
			list.add(transactionDao.getTransaction(Integer.parseInt(id)));
		}
//		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.action.equals("")) {
			try {
				transaction = (QdTransaction)initialize(QdTransaction.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		initialize(request, response);
		transaction.setTid(Integer.parseInt(id));
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
	
	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdTransaction.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(transaction, json.get(json.keySet().toArray()[i]));
				}
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
