package com.qingdy.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.SpecialistDao;
import com.qingdy.model.Score;
import com.qingdy.model.UserDetail;
import com.qingdy.model.domain.Specialist;

@Service("specialistDao")
public class SpecialistDaoHibernate extends BaseDaoHibernate implements SpecialistDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialist> getSpecialists(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		List<Specialist> specialists = new ArrayList<>();
		
		List<Object[]> lists = getHibernateTemplate().findByNamedQuery("querySpecialist", "%" + value + "%");
		if (lists.size() > page * size) {
			lists = lists.subList((page - 1) * size, page * size);
		}
		for (int i = 0; i < lists.size(); i++) {
			Specialist specialist = new Specialist();
			
			UserDetail poster = (UserDetail)lists.get(i)[0];

			specialist.setUser(poster);
			
			Long s = new Long(lists.get(i)[1].toString());
			specialist.setScores(s);
			
			specialist.setClasses(lists.get(i)[2].toString());
			
			specialists.add(specialist);
		}
		return specialists;
	}

	@Override
	public List<Specialist> getSpecialists(int size, int page, String[] field,
			String[] value, String operator[], String sidx, String sord, boolean verify) {
		
		List<Specialist> specialists = new ArrayList<>();
		System.out.println(field.length + value.length + operator.length + "*******************");
		
		Object[] values = new Object[2];
		values[0] = value[0];
		values[1] = "%" + value[1] + "%";
		List<Object[]> lists = getHibernateTemplate().findByNamedQuery("querySpecialistWithFilters", values);
		if (lists.size() > page * size) {
			lists = lists.subList((page - 1) * size, page * size);
		}
		for (int i = 0; i < lists.size(); i++) {
			Specialist specialist = new Specialist();
			
			UserDetail poster = (UserDetail)lists.get(i)[0];

			specialist.setUser(poster);
			
			Long s = new Long(lists.get(i)[1].toString());
			specialist.setScores(s);
			
			specialist.setClasses(lists.get(i)[2].toString());
			
			specialists.add(specialist);
		}
		return specialists;
	}

}
