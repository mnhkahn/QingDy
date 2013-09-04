package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.qingdy.common.Constant;
import com.qingdy.dao.VisitDao;
import com.qingdy.model.UserDetail;
import com.qingdy.model.Visit;

@Service("visitDao")
public class VisitDaoHibernate extends BaseDaoHibernate implements VisitDao {

	@Override
	public void addVisit(Visit visit) {
		getHibernateTemplate().save(visit);
	}

	@Override
	public List<Visit> getVisits(String username) {
		UserDetail user = getHibernateTemplate().get(UserDetail.class, username);
		
		return null;
	}

	@Override
	public List<Visit> getVisits(int type, Long id, int size, int page) {
		List<Visit> visits = null;
		switch (type) {
		case 5:
			visits = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("type", Constant.MALL)).add(Restrictions.eq("oid", id)).addOrder(Order.desc("date")));
			break;

		default:
			break;
		}
		return visits;
	}

}
