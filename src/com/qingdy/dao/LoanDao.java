package com.qingdy.dao;

import java.util.List;

import com.qingdy.common.SQLParameters;
import com.qingdy.domain.Grid;
import com.qingdy.domain.QdLoan;

public interface LoanDao {
	
	public int addLoan(QdLoan loan);
	
	public Grid getLoanList(SQLParameters parameters);
	
	public QdLoan getLoan(int lid);
	
	public List<QdLoan> getLoanByUser(String username, int size, int page);
	
	public int updateLoan(QdLoan loan);
	
	public int verifyLoan(int lid, int verify);
	
	public int removeLoan(int lid);

}
