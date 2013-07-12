package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.Grid;
import com.qingdy.domain.QdLoanPawn;

public interface LoanPawnDao {

	public int addLoanPawn(List<QdLoanPawn> loanPawns);
	
	public Grid getLoanPawn(int lid);
	
	public int deleteLoanPawn(int lid);
	
	public int updateLoanPawn(int lid, List<QdLoanPawn> loanPawns);
}
