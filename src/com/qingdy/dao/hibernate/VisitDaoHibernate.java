package com.qingdy.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.qingdy.common.Constant;
import com.qingdy.dao.VisitDao;
import com.qingdy.model.UserDetail;
import com.qingdy.model.Visit;
import com.qingdy.model.domain.VisitDate;

@Service("visitDao")
public class VisitDaoHibernate extends BaseDaoHibernate implements VisitDao {

	@Override
	public void addVisit(Visit visit) {
		getHibernateTemplate().save(visit);
	}

	@Override
	public List<Visit> getVisits(int type, Long id, int size, int page) {
		List<Visit> visits = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("type", type)).add(Restrictions.eq("oid", id)).addOrder(Order.desc("startDate")), size * (page - 1), size);
		return visits;
	}

	@Override
	public int getVisits(int type, Long id) {
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("type", type)).add(Restrictions.eq("oid", id))).size();
	}

	@Override
	public List<Visit> getUserVisits(String username, int size, int page) {
		UserDetail user = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Visit.class).add(Restrictions.eq("user", user)), size * (page - 1), size);
	}

	@Override
	public List<VisitDate> getVisits(int type, Long id, Date startTime,
			Date endTime) {
		Object [] values = {id, type, startTime, endTime};

		System.out.println("queryVisitByDate");
		System.out.println(values[0].toString() + values[1].toString() + values[2]);
		List<VisitDate> visitDates = new ArrayList<VisitDate>();
		List<Object []> visits = getHibernateTemplate().findByNamedQuery("queryVisitByDate", values);
		
		for (int i = 0; i < visits.size(); i++) {
			Date date = (Date)visits.get(i)[1];
			VisitDate visitDate = new VisitDate(id, type, new Integer(visits.get(i)[0].toString()), (1900 + date.getYear()) + "-" + (date.getMonth() + 1) + "-" + date.getDay());
			visitDates.add(visitDate);
		}
		
		System.out.println(visits.size());
		return visitDates;

	}

}
