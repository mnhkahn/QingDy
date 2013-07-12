package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdProductClients;

public interface ProductClientsDao {

	public int addProductClients(List<QdProductClients> productClients);
	
	public Grid getProductClients(int pid);
	
	public int deleteProductClients(int pid);
	
	public int updateProductClients(int pid, List<QdProductClients> productClients);
}
