package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Loan;

public interface LoanDao {
	
	public List<Loan> getLoans(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public List<Loan> getLoans(int size, int page, String field[], String value[], String operator[], String sidx, String sord, boolean verify);
	
	public Integer getLoansCount(String field[], String value[], String operator[], boolean verify);
	
	public List<Loan> getLoans(String username);
	
	public void saveLoan(Loan loan);
	
	public Loan getLoan(Long id);

	public void verifyLoan(Long id, boolean verify);
	
	public void removeLoan(Long id);

}
