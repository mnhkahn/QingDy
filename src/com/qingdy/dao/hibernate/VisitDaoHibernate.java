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
	public List<Visit> getVisits(int type, Long id, int size, int page) {
		List<Visit> visits = null;
		switch (type) {
		case 5:
			visits = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("type", Constant.MALL)).add(Restrictions.eq("oid", id)).addOrder(Order.desc("date")), size * (page - 1), size);
			break;

		default:
			break;
		}
		return visits;
	}

	@Override
	public int getVisits(int type, Long id) {
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("type", Constant.MALL)).add(Restrictions.eq("oid", id))).size();
	}

	@Override
	public List<Visit> getUserVisits(String username, int size, int page) {
		UserDetail user = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("user", user)), size * (page - 1), size);
	}

}
