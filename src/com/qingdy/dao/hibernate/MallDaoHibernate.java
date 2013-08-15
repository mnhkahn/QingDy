package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.MallDao;
import com.qingdy.model.Mall;

@Service("mallDao")
public class MallDaoHibernate extends BaseDaoHibernate implements MallDao {

	@Override
	public List<Mall> getVerifiedMalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mall> getMalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mall getMall(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mall getMall(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMall(Mall mall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyMall(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMall(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
