package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdMall;

public interface MallDao {

	public List<QdMall> getMallList(int size, int page, String keyword);
	
	public QdMall getMall(int mid);
	
	public QdMall getMallByUser(String username);
	
	public int updateMall(String username);
}
