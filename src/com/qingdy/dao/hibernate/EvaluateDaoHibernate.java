package com.qingdy.dao.hibernate;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

import com.qingdy.dao.EvaluateDao;
import com.qingdy.model.Evaluate;

@Service("evaluateDao")
public class EvaluateDaoHibernate extends BaseDaoHibernate implements
		EvaluateDao {

	@Override
	public Evaluate getEvaluate(Long evaluateId) {
		Evaluate evaluate = (Evaluate)getHibernateTemplate().get(Evaluate.class, evaluateId);
		if (evaluate == null) {
			throw new ObjectRetrievalFailureException(Evaluate.class, evaluateId);
		}
		return evaluate;
	}

	@Override
	public void saveBlogEvaluate(Evaluate evaluate) {
		getHibernateTemplate().saveOrUpdate(evaluate);
	}

	@Override
	public void removeEvaluate(Long evaluateId) {
		getHibernateTemplate().delete(evaluateId);
	}

	@Override
	public List getBlogEvaluate() {
		return getHibernateTemplate().find("from Evaluate");
	}

}
