package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdBanks;

public interface BankDao {

	public int addBank(QdBanks bank);
	
	public List<QdBanks> getBankList(int size, int page);
	
	public QdBanks getBank(int bid);
	
	public int updateBank(QdBanks bank);
	
	public int removeBank(int bid);
}
