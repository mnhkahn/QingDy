package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Grid;
import com.qingdy.model.QdLoanPawn;

public interface LoanPawnDao {

	public int addLoanPawn(List<QdLoanPawn> loanPawns);
	
	public Grid getLoanPawn(int lid);
	
	public int deleteLoanPawn(int lid);
	
	public int updateLoanPawn(int lid, List<QdLoanPawn> loanPawns);
}
