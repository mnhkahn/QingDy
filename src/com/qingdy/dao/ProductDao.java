package com.qingdy.dao;

import java.util.List;
import com.qingdy.model.Product;

public interface ProductDao {
	
	public void saveProduct(Product product);
	
	public List<Product> getProducts();
	
	public Product getProduct(Long id);
	
	public List<Product> getProduct(String username);

	public void verifyProduct(Long id, boolean verify);
	
	public void removeProduct(Long id);	
	
}
