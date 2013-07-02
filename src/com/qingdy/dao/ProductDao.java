package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdProduct;

public interface ProductDao {
	
	public int addProduct(QdProduct product);
	
	public Grid getProductList(SQLParameters parameters);
	
	public QdProduct getProduct(int pid);
	
	public List<QdProduct> getProductByUser(String username);

	public int updateProduct(QdProduct product);
	
	public int verifyProduct(int pid, int verify);
	
	public int removeProduct(int pid);	
	
}
