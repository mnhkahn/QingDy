package com.qingdy.service;

import java.io.IOException;
import java.util.LinkedList;

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
	private QdMember user = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Member() {
		super();
		userDao = new UserDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Get user
		if (this.action.equals("")) {
			userDao.getUid(this.id);
		}
		// Get user post
		else if (this.action.equals("done")) {

		} 
		else {

		}
		response.getWriter().write(toJson());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		list = new LinkedList<>();
		// Login
		if (this.action.equals("login")) {
			try {
				user = (QdMember)initialize(QdMember.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			QdMember loginUser = userDao.getUser(user.getUsername(), user.getPassword());
			
			if (loginUser != null) {
				list.add(loginUser);
			}
			else {
				System.out.println("Login error!");
			}
			response.getWriter().write(toJson());
		}
		// Register
		else if (this.action.equals("register")) {
			try {
				user = (QdMember)initialize(QdMember.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			user = (QdMember)initialize(QdMember.class, request, response);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Update user info
		if (this.action.equals("")) {
			userDao.updateUser(user);
		}
		super.doPut(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		super.doDelete(req, resp);
	}

}
