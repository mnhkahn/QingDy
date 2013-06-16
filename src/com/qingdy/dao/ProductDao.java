package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdProduct;

public interface ProductDao {

	public int updateProduct(QdProduct product);
	
	public List<QdProduct> getProducts(int size, int page, String keyword);
	
	public int addProduct(QdProduct product);
}
