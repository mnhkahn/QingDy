package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.QuestionDao;
import com.qingdy.dao.impl.QuestionDaoImpl;
import com.qingdy.domain.QdQuestion;

/**
 * Servlet implementation class Question
 */
@WebServlet("/Question")
public class Question extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private QuestionDao questionDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		questionDao = new QuestionDaoImpl();
		
		if (this.size > 1) {
			List<QdQuestion> questions = questionDao.getQdQuestionList(size, page, keyword);
		}
		else if (this.size == 1) {
			QdQuestion question = questionDao.getQuestion(Integer.parseInt(this.id));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		questionDao = new QuestionDaoImpl();
		
		QdQuestion question = new QdQuestion();
		
		// Create one question
		if (action.equals("")) {
			questionDao.addQuestion(question);
		}
		if (action.equals("positive")) {
			questionDao.verifyQuestion(Integer.parseInt(id), 1);
		}
		if (action.equals("negative")) {
			questionDao.verifyQuestion(Integer.parseInt(id), 0);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		questionDao = new QuestionDaoImpl();
		
		QdQuestion question = new QdQuestion();
		
		if (action.equals("")) {
			questionDao.updateQuestion(question);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		questionDao = new QuestionDaoImpl();
		
		if (action.equals("")) {
			questionDao.removeQuestion(Integer.parseInt(id));
		}
	}

}
