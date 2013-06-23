package com.qingdy.service;

import java.io.IOException;

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
	private QdQuestion question = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question() {
        super();
        questionDao = new QuestionDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list.clear();
		if (size > 1) {
			list = questionDao.getQdQuestionList(size, page, keyword);
		}
		else if (this.size == 1) {
			String username = request.getParameter("username");
			if (username != null && !username.equals("")) {
				list = questionDao.getQuestionByUser(username);
			}
			else if (!id.equals("-1")) {
				list.add(questionDao.getQuestion(Integer.parseInt(this.id)));
			}
		}
		
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create one question
		if (action.equals("")) {
			try {
				question = (QdQuestion)initialize(QdQuestion.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		try {
			question = (QdQuestion)initialize(QdQuestion.class, request, response);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		question.setQid(Integer.parseInt(id));
		if (action.equals("")) {
			questionDao.updateQuestion(question);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			questionDao.removeQuestion(Integer.parseInt(id));
		}
	}

}
