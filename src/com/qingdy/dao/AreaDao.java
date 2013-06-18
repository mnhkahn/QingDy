package com.qingdy.dao;

import java.awt.geom.Area;
import java.util.List;

public interface AreaDao {
	
	public List<Area> getProvince();
	
	public List<Area> getCity(int province);
	
	public List<Area> getCounty(int city);
	
	public List<Area> getFullLocation(int areaId);
}
