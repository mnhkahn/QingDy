package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.qingdy.common.CServlet;
import com.qingdy.dao.LoanDao;
import com.qingdy.dao.impl.LoanDaoImpl;
import com.qingdy.domain.QdLoan;

/**
 * Servlet implementation class Loan
 */
@WebServlet("/Loan")
public class Loan extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private LoanDao loanDao;
	private QdLoan loan = new QdLoan();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loan() {
        super();
        loanDao = new LoanDaoImpl();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		
		if (action.equals("verify")) {
			object = loanDao.getLoanList(parameters);
			
			String json = JSONSerializer.toJSON(object , jsonConfig).toString();
			response.getWriter().write(json);
		}
		else if (parameters.getSize() == 1) {
			list.add(loanDao.getLoan(Integer.parseInt(id)));
		}
		else {
			
		}
		
//		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		if (this.action.equals("")) {
			try {
				loan = (QdLoan)initialize(QdLoan.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loanDao.addLoan(loan);
		}
		if (action.equals("")) {
			loanDao.addLoan(loan);
		}
		else if (action.equals("positive")) {
			loanDao.verifyLoan(Integer.parseInt(id), 1);
		}
		else if (action.equals("negative")) {
			System.out.println("negative");
			loanDao.verifyLoan(Integer.parseInt(id), 0);
		}
		else {
			
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		initialize(request, response);
		
		if (action.equals("")) {
			loanDao.updateLoan(loan);
		}
		else {
			
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		
		if (action.equals("")) {
			loanDao.removeLoan(Integer.parseInt(id));
		}
	}

	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdLoan.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(loan, json.get(json.keySet().toArray()[i]));
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
