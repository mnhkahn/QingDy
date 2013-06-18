package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.AnswerDao;
import com.qingdy.dao.impl.AnswerDaoImpl;
import com.qingdy.domain.QdAnswer;

/**
 * Servlet implementation class Answer
 */
@WebServlet("/Answer")
public class Answer extends CServlet {
	private static final long serialVersionUID = 1L;
	
	private AnswerDao answerDao = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Answer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		answerDao = new AnswerDaoImpl();
		
		// Get answer of question
		if (id.equals("")) {
			int qid = Integer.parseInt(request.getParameter("qid"));
			List<QdAnswer> answers = answerDao.getAnswerByQuestion(qid);
		}
		else {
			QdAnswer answer = answerDao.getAnswer(Integer.parseInt(id));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		answerDao = new AnswerDaoImpl();
		
		QdAnswer answer = new QdAnswer();
		
		if (action.equals("")) {
			answerDao.addAnswer(answer);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		answerDao = new AnswerDaoImpl();
		
		QdAnswer answer = new QdAnswer();
		
		if (action.equals("")) {
			answerDao.updateAnswer(answer);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		answerDao = new AnswerDaoImpl();
		
		if (action.equals("")) {
			answerDao.removeAnswer(Integer.parseInt(id));
		}
	}

}
