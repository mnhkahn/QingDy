package com.qingdy.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

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
	public List<Score> getSpecialists(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		List<Score> scores = new ArrayList<>();
		
		Object[] values = new Object[2];
		values[0] = (page - 1) * size + 1;
		values[1] = size;
		List<Object[]> lists = getHibernateTemplate().findByNamedQuery("querySpecialist").subList((page - 1) * size, page * size);
		
		for (int i = 0; i < lists.size(); i++) {
			Score score = new Score();
			
			UserDetail poster = (UserDetail)lists.get(i)[0];

			score.setPoster(poster);
			
			Integer s = new Integer(lists.get(i)[1].toString());
			score.setScore(s);
			
			scores.add(score);
		}
		return scores;
	}

	@Override
	public List<Specialist> getSpecialists(int size, int page, String[] field,
			String[] value, String operator[], String sidx, String sord, boolean verify) {
		
		List<Specialist> specialists = new ArrayList<>();
		
		Object[] values = new Object[2];
		values[0] = Integer.parseInt(value[0]);
//		values[1] = size;
		List<Object[]> lists = getHibernateTemplate().findByNamedQuery("querySpecialistWithFilters", Integer.parseInt(value[0]));
		
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
