package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.TransactionDao;
import com.qingdy.model.Blog;
import com.qingdy.model.Transaction;
import com.qingdy.model.UserDetail;

@Service("transactionDao")
public class TransactionDaoHibernate extends BaseDaoHibernate implements TransactionDao {

	@Override
	public void saveTransaction(Transaction transaction) {
		getHibernateTemplate().saveOrUpdate(transaction);
	}

	@Override
	public List<Transaction> geTransactions(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Transaction.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}

	@Override
	public Transaction getTransaction(Long id) {
		Transaction transaction = getHibernateTemplate().get(Transaction.class, id);
		if (transaction == null) {
			throw new ObjectRetrievalFailureException(Transaction.class, id);
		}
		return transaction;
	}

	@Override
	public List<Transaction> getTransactions(String username) {
		UserDetail poster = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Transaction.class).add(Restrictions.eq("lender", poster)).add(Restrictions.eq("verify", 1)));
	}

	@Override
	public void verifyTransaction(Long id, boolean verify) {
		Transaction transaction = getTransaction(id);
		if (verify) {
			transaction.setVerify(1);
		}
		else {
			transaction.setVerify(0);
		}
		saveTransaction(transaction);
	}

	@Override
	public void removeTransaction(Long id) {
		Transaction transaction = getTransaction(id);
		getHibernateTemplate().delete(transaction);
	}
	
	@Override
	public Long getTransactionCount() {
		return new Long(getHibernateTemplate().findByNamedQuery("queryTransactionCount").size());
	}

}
