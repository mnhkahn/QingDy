package com.qingdy.dao;

import java.util.List;
import com.qingdy.model.Mall;

public interface MallDao {
	
	public List<Mall> getVerifiedMalls();
	
	public List<Mall> getMalls();
	
	public Mall getMall(Long id);
	
	public Mall getMall(String username);
	
	public void saveMall(Mall mall);
	
	public void verifyMall(Long id, boolean verify);
	
	public void removeMall(Long id);
}
