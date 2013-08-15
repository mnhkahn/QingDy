package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.LoanDao;
import com.qingdy.model.Loan;

@Service("loanDao")
public class LoanDaoHibernate extends BaseDaoHibernate implements LoanDao {

	@Override
	public List<Loan> getLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoans(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveLoan(Loan loan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Loan getLoan(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyLoan(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLoan(Long id) {
		// TODO Auto-generated method stub
		
	}


}
