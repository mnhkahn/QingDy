package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.common.cRestrictions;
import com.qingdy.dao.QuestionDao;
import com.qingdy.model.Answer;
import com.qingdy.model.Blog;
import com.qingdy.model.Question;
import com.qingdy.model.User;
import com.qingdy.model.UserDetail;

@Service("questionDao")
public class QuestionDaoHibernate extends BaseDaoHibernate implements QuestionDao {

	@Override
	public void saveQuestion(Question question) {
		getHibernateTemplate().saveOrUpdate(question);
	}

	@Override
	public List<Question> getQuestions(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Question.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}

	@Override
	public Question getQuestion(Long id) {
		Question question = getHibernateTemplate().get(Question.class, id);
		if (question == null) {
			throw new ObjectRetrievalFailureException(Question.class, id);
		}
		return question;
	}

	@Override
	public List<Question> getQuestion(String username) {
		UserDetail userDetail = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Question.class).add(Restrictions.eq("poster", userDetail)));
	}

	@Override
	public void verifyQuestion(Long id, boolean verify) {
		Question question = getQuestion(id);
		if (verify) {
			question.setVerify(1);
		}
		else {
			question.setVerify(0);
		}
		saveQuestion(question);
	}

	@Override
	public void removeQuestion(Long id) {
		Question question = getQuestion(id);
		getHibernateTemplate().delete(question);
	}

	@Override
	public void bestAnswer(Long qid, Long aid) {
		Question question = getQuestion(qid);
		question.setBest(aid);
		saveQuestion(question);
	}

}
