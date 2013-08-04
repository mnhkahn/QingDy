package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdMallUsesofloan;

public interface MallUsesofloanDao {

	public int addMallUsesofloan(List<QdMallUsesofloan> mallUsesofloans);
	
	public Grid getMallUsesofloan(int mid);
	
	public int deleteMallUsesofloan(int mid);
	
	public int updateMallUsesofloan(int mid, List<QdMallUsesofloan> mallUsesofloans);
}
