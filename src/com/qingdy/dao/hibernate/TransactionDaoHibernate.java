package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.TransactionDao;
import com.qingdy.model.Transaction;

@Service("transactionDao")
public class TransactionDaoHibernate extends BaseDaoHibernate implements TransactionDao {

	@Override
	public void saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Transaction> geTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransaction(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransaction(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyTransaction(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTransaction(Long id) {
		// TODO Auto-generated method stub
		
	}

}
