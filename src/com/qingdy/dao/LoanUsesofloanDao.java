package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdLoanUsesofloan;

public interface LoanUsesofloanDao {

	public int addLoanUsesofloan(List<QdLoanUsesofloan> loanUsesofloans);
	
	public Grid getLoanUsesofloan(int lid);
	
	public int deleteLoanUsesofloan(int lid);
	
	public int updateLoanUsesofloan(int lid, List<QdLoanUsesofloan> loanUsesofloans);
}
