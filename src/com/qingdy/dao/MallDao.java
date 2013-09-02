package com.qingdy.dao;

import java.util.List;
import com.qingdy.model.Mall;

public interface MallDao {

	public List<Mall> getMalls(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Mall getMall(Long id);
	
	public Mall getMall(String username);
	
	public void saveMall(Mall mall);
	
	public void verifyMall(Long id, boolean verify);
	
	public void removeMall(Long id);
	
	public Long getMallCount();
}
