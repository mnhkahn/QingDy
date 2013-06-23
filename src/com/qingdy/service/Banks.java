package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.domain.QdBanks;
import com.qingdy.dao.BankDao;
import com.qingdy.dao.impl.BankDaoImpl;

/**
 * Servlet implementation class Banks
 */
@WebServlet("/Banks")
public class Banks extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private BankDao bankDao = null;
	private QdBanks bank = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Banks() {
        super();
        bankDao = new BankDaoImpl();
        
        bank = new QdBanks();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (size > 1) {
			list = bankDao.getBankList(size, page);
		}
		else if (size == 1) {
			list.add(bankDao.getBank(Integer.parseInt(id)));
		}
		
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialize(request, response);
		if (action.equals("")) {
			bankDao.addBank(bank);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialize(request, response);
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
	
	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdBanks.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(bank, json.get(json.keySet().toArray()[i]));
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
