package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.MallDao;
import com.qingdy.model.Blog;
import com.qingdy.model.Mall;
import com.qingdy.model.UserDetail;

@Service("mallDao")
public class MallDaoHibernate extends BaseDaoHibernate implements MallDao {

	@Override
	public List<Mall> getMalls(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Mall.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}
	

	@Override
	public Integer getMallsCount(String[] field, String[] value,
			String[] operator, boolean verify) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Mall.class, field, value, operator, verify)).size();
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
		UserDetail poster = getHibernateTemplate().get(UserDetail.class, username);
		return (Mall)getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Mall.class).add(Restrictions.eq("poster", poster))).get(0);
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

	@Override
	public Long getMallCount() {
		return new Long(getHibernateTemplate().findByNamedQuery("queryMallCount").size());
	}

}
