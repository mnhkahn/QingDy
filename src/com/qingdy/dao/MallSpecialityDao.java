package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdMallSpeciality;

public interface MallSpecialityDao {
	
	public int addMallSpeciality(List<QdMallSpeciality> mallSpecialities);
	
	public Grid getMallSpeciality(int mid);
	
	public int deleteMallSpeciality(int mid);
	
	public int updateMallSpeciality(int mid, List<QdMallSpeciality> mallSpecialities);
}
