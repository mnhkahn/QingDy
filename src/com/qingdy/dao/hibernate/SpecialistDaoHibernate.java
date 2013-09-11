package com.qingdy.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.MallDao;
import com.qingdy.dao.SpecialistDao;
import com.qingdy.model.Blog;
import com.qingdy.model.Score;
import com.qingdy.model.UserDetail;
import com.qingdy.model.domain.Specialist;

@Service("specialistDao")
public class SpecialistDaoHibernate extends BaseDaoHibernate implements SpecialistDao {

	@Override
	public List getObjects(Class cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Class cls, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveObject(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeObject(Class cls, Serializable id) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Score> getSpecialists(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		List<Score> scores = new ArrayList<>();
		List<Object[]> lists = getHibernateTemplate().findByNamedQuery("querySpecialist");
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

}
