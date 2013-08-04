package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdProductPawn;

public interface ProductPawnDao {

	public int addProductPawn(List<QdProductPawn> productPawns);
	
	public Grid getProductPawn(int pid);
	
	public int deleteProductPawn(int pid);
	
	public int updateProductPawn(int pid, List<QdProductPawn> productPawns);
}
