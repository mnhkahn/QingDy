package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.QuestionclassesDao;
import com.qingdy.dao.impl.QuestionclassesDaoImpl;

/**
 * Servlet implementation class QuestionClasses
 */
@WebServlet("/QuestionClasses")
public class QuestionClasses extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private QuestionclassesDao questionclassesDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionClasses() {
        super();
        questionclassesDao = new QuestionclassesDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = questionclassesDao.getQuestionclasses();
		}
		
		super.doGet(request, response);
	}

}
