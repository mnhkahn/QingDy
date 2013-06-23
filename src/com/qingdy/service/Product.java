package com.qingdy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.common.Constant;
import com.qingdy.dao.ProductDao;
import com.qingdy.dao.impl.ProductDaoImpl;
import com.qingdy.domain.QdProduct;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends CServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductDao productDao = null;
	private QdProduct product = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        productDao = new ProductDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list.clear();
		
		if (this.size > 1) {
			list = productDao.getProductList(this.size, this.page, this.keyword);
		}
		else if (this.size == 1) {
			String username = request.getParameter("username");
			if (username != null) {
				list = productDao.getProductByUser(username);
			}
			else if (!id.equals("-1")) {
				list.add(productDao.getProduct(Integer.parseInt(this.id)));
			}
		}
		
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create one product
		if (this.action.equals("")) {
			try {
				product = (QdProduct)initialize(QdProduct.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			productDao.addProduct(product);
		}
		else if (this.action.equals("positive")) {
			productDao.verifyProduct(Integer.parseInt(this.id), Constant.POSITIVE);
		}
		else if (this.action.equals("negative")) {
			productDao.verifyProduct(Integer.parseInt(this.id), Constant.NEGATIVE);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (this.action.equals("")) {
			try {
				product = (QdProduct)initialize(QdProduct.class, request, response);
			} catch (NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			product.setPid(Integer.parseInt(id));
			productDao.updateProduct(product);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (this.action.equals("")) {
			productDao.removeProduct(Integer.parseInt(this.id));
		}
	}

}
