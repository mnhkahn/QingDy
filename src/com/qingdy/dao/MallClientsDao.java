package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdMallClients;

public interface MallClientsDao {

	public int addMallClients(List<QdMallClients> mallClients);
	
	public int getMallClients(int mid);
	
	public int deleteMallClients(int mid);
	
	public int updateMallClients(int mid, List<QdMallClients> mallClients);
}
