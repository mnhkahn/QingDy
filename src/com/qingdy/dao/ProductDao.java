package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Product;

public interface ProductDao {
	
	public void saveProduct(Product product);
	
	public List<Product> getProducts(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public List<Product> getProducts(int size, int page, String field[], String value[], String operator[], String sidx, String sord, boolean verify);
	
	public Integer getProductsCount(String field[], String value[], String operator[], boolean verify);
	
	public Product getProduct(Long id);
	
	public List<Product> getProduct(String username);

	public void verifyProduct(Long id, boolean verify);
	
	public void removeProduct(Long id);	
	
}
