package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.common.Constant;
import com.qingdy.dao.AnswerDao;
import com.qingdy.dao.impl.AnswerDaoImpl;
import com.qingdy.domain.QdAnswer;
import com.qingdy.domain.QdNews;

/**
 * Servlet implementation class Answer
 */
@WebServlet("/Answer")
public class Answer extends CServlet {
	private static final long serialVersionUID = 1L;
	
	private QdAnswer answer = null;
	private AnswerDao answerDao = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Answer() {
        super();
        answerDao = new AnswerDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list.clear();
		
		// Get answer of question
		if (id.equals("-1")) {
			int qid = Integer.parseInt(request.getParameter("qid"));
			list = answerDao.getAnswerByQuestion(qid);
		}
		else {
			String username = request.getParameter("username");
			if (username != null && !username.equals("")) {
				list = answerDao.getAnswerByUser(username);
			}
			else if (!id.equals("-1")){
				list.add(answerDao.getAnswer(Integer.parseInt(id)));
			}
		}
		
		super.doGet(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			try {
				answer = (QdAnswer)initialize(QdAnswer.class, request, response);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerDao.addAnswer(answer);
		}
		else if (action.equals("positive")) {
			answerDao.verifyAnswer(Integer.parseInt(id), Constant.POSITIVE);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			answer = (QdAnswer)initialize(QdAnswer.class, request, response);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		answer.setAid(Integer.parseInt(id));
		
		if (action.equals("")) {
			answerDao.updateAnswer(answer);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			answerDao.removeAnswer(Integer.parseInt(id));
		}
	}

}
