package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdTransaction;

public interface TransactionDao {

	public int addTransaction(QdTransaction transaction);
	
	public List<QdTransaction> geTransactionList(int size, int page);
	
	public QdTransaction getTransaction(int tid);
	
	public List<QdTransaction> getTransactionByUser(String username, int size, int page);
	
	public int updateTransaction(QdTransaction transaction);
	
	public int verifyTransaction(int tid, int verify);
	
	public int removeTransaction(int lid);
}