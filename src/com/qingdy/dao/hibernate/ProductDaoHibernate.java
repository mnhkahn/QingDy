package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.ProductDao;
import com.qingdy.model.Product;

@Service("productDao")
public class ProductDaoHibernate extends BaseDaoHibernate implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProduct(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyProduct(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

}
