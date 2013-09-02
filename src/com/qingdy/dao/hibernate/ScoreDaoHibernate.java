package com.qingdy.dao.hibernate;

import org.springframework.stereotype.Service;

import com.qingdy.dao.ScoreDao;
import com.qingdy.model.Score;

@Service("scoreDao")
public class ScoreDaoHibernate extends BaseDaoHibernate implements ScoreDao {

	@Override
	public void addScore(Score score) {
		getHibernateTemplate().save(score);
	}

	@Override
	public Long getSpecialistCount() {
		return new Long(getHibernateTemplate().findByNamedQuery("querySpecialistCount").size());
	}

}
