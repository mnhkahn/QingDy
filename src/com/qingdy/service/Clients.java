package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.ClientsDao;
import com.qingdy.dao.impl.ClientsDaoImpl;

/**
 * Servlet implementation class Clients
 */
@WebServlet("/Clients")
public class Clients extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private ClientsDao clientsDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Clients() {
        super();
        
        clientsDao = new ClientsDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (action.equals("")) {
			list = clientsDao.getClients();
		}
		
		super.doGet(request, response);
	}

}
