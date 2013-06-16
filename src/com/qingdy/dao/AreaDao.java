package com.qingdy.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AreaDao {
	
	public String getProvince();
	
	public String getCity(String province) throws UnsupportedEncodingException;
	
	public String getCounty(String province, String city) throws UnsupportedEncodingException;
	
	public List<String> getCounty(String username);
	
	public String getApartment(int areaid);
	
	public String getAllCities(String province) throws UnsupportedEncodingException;
}
