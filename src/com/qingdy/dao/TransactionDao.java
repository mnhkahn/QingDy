package com.qingdy.dao;

import java.util.List;
import com.qingdy.model.Transaction;

public interface TransactionDao {

	public void saveTransaction(Transaction transaction);
	
	public List<Transaction> geTransactions(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Transaction getTransaction(Long id);
	
	public List<Transaction> getTransaction(String username);

	public void verifyTransaction(Long id, boolean verify);
	
	public void removeTransaction(Long id);
}
