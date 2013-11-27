package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.LoanDao;
import com.qingdy.model.Blog;
import com.qingdy.model.Loan;
import com.qingdy.model.UserDetail;

@Service("loanDao")
public class LoanDaoHibernate extends BaseDaoHibernate implements LoanDao {

	@Override
	public List<Loan> getLoans(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Loan.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}
	
	@Override
	public List<Loan> getLoans(int size, int page, String[] field,
			String[] value, String operator[], String sidx, String sord, boolean verify) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Loan.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}


	@Override
	public List<Loan> getLoans(String username) {
		UserDetail user = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Loan.class).add(Restrictions.ge("poster", user)));
	}

	@Override
	public void saveLoan(Loan loan) {
		getHibernateTemplate().saveOrUpdate(loan);
	}

	@Override
	public Loan getLoan(Long id) {
		Loan loan = getHibernateTemplate().get(Loan.class, id);
		if (loan == null) {
			throw new ObjectRetrievalFailureException(Loan.class, id);
		}
		return loan;
	}

	@Override
	public void verifyLoan(Long id, boolean verify) {
		Loan loan = getHibernateTemplate().get(Loan.class, id);
		if (verify) {
			loan.setVerify(1);
		}
		else {
			loan.setVerify(0);
		}
		getHibernateTemplate().update(loan);
	}

	@Override
	public void removeLoan(Long id) {
		Loan loan = getLoan(id);
		getHibernateTemplate().delete(loan);
	}

}
