package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdMallAreas;

public interface MallAreasDao {

	public int addMallAreas(List<QdMallAreas> mallAreas);
	
	public Grid getMallAreas(int mid);
	
	public int deleteMallAreas(int mid);
	
	public int updateMallAreas(int mid, List<QdMallAreas> mallAreas);
}
