package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdProductUsesofloan;

public interface ProductUsesofloanDao {

	public int addProductUsesofloan(List<QdProductUsesofloan> productUsesofloans);
	
	public Grid getProductUsesofloan(int pid);
	
	public int deleteProductUsesofloan(int pid);
	
	public int updateProductUsesofloan(int pid, List<QdProductUsesofloan> productUsesofloans);
}
