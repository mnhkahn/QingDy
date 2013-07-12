package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdProductPawn;

public interface ProductPawnDao {

	public int addProductPawn(List<QdProductPawn> productPawns);
	
	public Grid getProductPawn(int pid);
	
	public int deleteProductPawn(int pid);
	
	public int updateProductPawn(int pid, List<QdProductPawn> productPawns);
}
