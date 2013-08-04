package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdProductClients;

public interface ProductClientsDao {

	public int addProductClients(List<QdProductClients> productClients);
	
	public Grid getProductClients(int pid);
	
	public int deleteProductClients(int pid);
	
	public int updateProductClients(int pid, List<QdProductClients> productClients);
}
