package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Areas;

public interface AreaDao {
	
	public List<Areas> getProvince();
	
	public List<Areas> getCity(int province);
	
	public List<Areas> getCounty(int city);
	
	public List<Areas> getFullLocation(int areaId);
}
