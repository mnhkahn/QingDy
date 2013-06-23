package com.qingdy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.SpecialityDao;
import com.qingdy.dao.impl.SpecialityDaoImpl;

/**
 * Servlet implementation class Speciality
 */
@WebServlet("/Speciality")
public class Speciality extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private SpecialityDao specialityDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Speciality() {
        super();
        specialityDao = new SpecialityDaoImpl(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (action.equals("")) {
			list = specialityDao.getSpecialities();
		}
		
		super.doGet(request, response);
	}

}
