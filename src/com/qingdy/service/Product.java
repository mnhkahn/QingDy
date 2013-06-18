package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.ProductDao;
import com.qingdy.dao.impl.ProductDaoImpl;
import com.qingdy.domain.QdProduct;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends CServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductDao productDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productDao = new ProductDaoImpl();
		
		if (this.size > 1) {
			List<QdProduct> products = productDao.getProductList(this.size, this.page, this.keyword);
		}
		else if (this.size == 1) {
			QdProduct product = productDao.getProduct(Integer.parseInt(this.id));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productDao = new ProductDaoImpl();
		
		QdProduct product = new QdProduct();
		
		// Create one product
		if (this.action.equals("")) {
			productDao.addProduct(product);
		}
		else if (this.action.equals("positive")) {
			productDao.verifyProduct(Integer.parseInt(this.id), 1);
		}
		else if (this.action.equals("negative")) {
			productDao.verifyProduct(Integer.parseInt(this.id), 0);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productDao = new ProductDaoImpl();
		
		QdProduct product = new QdProduct();
		
		if (this.action.equals("")) {
			productDao.updateProduct(product);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productDao = new ProductDaoImpl();
		
		if (this.action.equals("")) {
			productDao.removeProduct(Integer.parseInt(this.id));
		}
	}

}
