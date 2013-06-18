package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.UserDao;
import com.qingdy.dao.impl.UserDaoImpl;
import com.qingdy.domain.QdMember;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends CServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		userDao = new UserDaoImpl();

		// Get user
		if (this.action.equals("")) {
			userDao.getUid(this.id);
		}
		// Get user post
		else if (this.action.equals("done")) {

		} else {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		userDao = new UserDaoImpl();
		
		QdMember user = new QdMember();

		// Login
		if (this.action.equals("login")) {
			QdMember loginUser = userDao.getUser(this.id);
			
			if (loginUser.getPassword().equals(user.getPassword())) {
				
			}
			else {
				
			}
		}
		// Register
		else if (this.action.equals("register")) {
			userDao.addUser(user);
		}
		else {
			
		}
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		userDao = new UserDaoImpl();
		// Username exists
		if (this.action.equals("exists")) {
			int id = userDao.getUid(this.id);
			if (id == -1) {
				
			}
			// exists
			else {
				
			}
		}
		
		super.doHead(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		QdMember user = new QdMember();
		
		// Update user info
		if (this.action.equals("")) {
			userDao.updateUser(user);
		}
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doDelete(req, resp);
	}

}
