package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.PawnDao;
import com.qingdy.dao.impl.PawnDaoImpl;

/**
 * Servlet implementation class Pawn
 */
@WebServlet("/Pawn")
public class Pawn extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private PawnDao pawnDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pawn() {
        super();
        pawnDao = new PawnDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = pawnDao.getPawn();
		}
		
		super.doGet(request, response);
	}

}
