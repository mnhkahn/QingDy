package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdMallAreas;

public interface MallAreasDao {

	public int addMallAreas(List<QdMallAreas> mallAreas);
	
	public Grid getMallAreas(int mid);
	
	public int deleteMallAreas(int mid);
	
	public int updateMallAreas(int mid, List<QdMallAreas> mallAreas);
}
