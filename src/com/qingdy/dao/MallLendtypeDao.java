package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdMallLendtype;

public interface MallLendtypeDao {

	public int addMallLendtype(List<QdMallLendtype> mallLendtypes);
	
	public Grid getMallLendtype(int mid);
	
	public int deleteMallLendtype(int mid);
	
	public int updateMallLendtype(int mid, List<QdMallLendtype> mallLendtypes);
}
