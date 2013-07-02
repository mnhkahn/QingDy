package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdMall;

public interface MallDao {
	
	public int addMall(QdMall mall);

	public List<QdMall> getVerifiedMallList(int size, int page, String keyword);
	
	public Grid getAllMallList(SQLParameters parameters);
	
	public QdMall getMall(int mid);
	
	public QdMall getMallByUser(String username);
	
	public int updateMall(QdMall mall);
	
	public int verifyMall(int mid, int verify);
	
	public int removeMall(int mid);
}
