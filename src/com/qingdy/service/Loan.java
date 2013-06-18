package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }
    
    private QdLoan initializeLoan(Map<String, Object> json) {
    	QdLoan loan = new QdLoan();
    	
    	return loan;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		
		if (size > 1) {
			QdLoan loan = loanDao.getLoan(Integer.parseInt(id));
		}
		else if (size == 1) {
			List<QdLoan> loans = loanDao.getLoanList(size, page, keyword);
		}
		else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		
		if (action.equals("")) {
			loanDao.addLoan(loan);
		}
		else if (action.equals("positive")) {
			loanDao.verifyLoan(Integer.parseInt(id), 1);
		}
		else if (action.equals("negative")) {
			loanDao.verifyLoan(Integer.parseInt(id), 1);
		}
		else {
			
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loanDao = new LoanDaoImpl();
		
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

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);

		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdLoan.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				if (method != null) {
					method.invoke(loan, json.get(json.keySet().toArray()[i]));
					break;
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
		loan = initializeLoan(json);
		
	}	

}
