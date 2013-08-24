package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.dao.MallDao;
import com.qingdy.model.Mall;

@Service("mallDao")
public class MallDaoHibernate extends BaseDaoHibernate implements MallDao {

	@Override
	public List<Mall> getMalls(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().find("from Mall");
	}

	@Override
	public Mall getMall(Long id) {
		Mall mall = (Mall)getHibernateTemplate().get(Mall.class, id);
		if (mall == null) {
			throw new ObjectRetrievalFailureException(Mall.class, id);
		}
		return mall;
	}

	@Override
	public Mall getMall(String username) {
		return getHibernateTemplate().get(Mall.class, username);
	}

	@Override
	public void saveMall(Mall mall) {
		getHibernateTemplate().saveOrUpdate(mall);
	}

	@Override
	public void verifyMall(Long id, boolean verify) {
		Mall mall = getMall(id);
		if (verify) {
			mall.setVerify(1);
		}
		else {
			mall.setVerify(0);
		}
		saveMall(mall);
	}

	@Override
	public void removeMall(Long id) {
		Mall mall = getMall(id);
		getHibernateTemplate().delete(mall);
	}

	

}
