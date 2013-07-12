package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdMallUsesofloan;

public interface MallUsesofloanDao {

	public int addMallUsesofloan(List<QdMallUsesofloan> mallUsesofloans);
	
	public Grid getMallUsesofloan(int mid);
	
	public int deleteMallUsesofloan(int mid);
	
	public int updateMallUsesofloan(int mid, List<QdMallUsesofloan> mallUsesofloans);
}
