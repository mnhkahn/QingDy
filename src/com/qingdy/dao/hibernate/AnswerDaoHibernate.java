package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.dao.AnswerDao;
import com.qingdy.model.Answer;
import com.qingdy.model.Question;

@Service("answerDao")
public class AnswerDaoHibernate extends BaseDaoHibernate implements AnswerDao {

	@Override
	public List<Answer> getAnswers(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().find("from answer");
	}

	@Override
	public void saveAnswer(Answer answer) {
		getHibernateTemplate().saveOrUpdate(answer);	
	}

	@Override
	public List<Answer> getAnswers(Long qid) {
		Question question = getHibernateTemplate().get(Question.class, qid);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Answer.class).add(Restrictions.ge("question", question)));
	}

	@Override
	public Answer getAnswer(Long id) {
		Answer answer = getHibernateTemplate().get(Answer.class, id);
		if (answer == null) {
			throw new ObjectRetrievalFailureException(Answer.class, id);
		}
		return answer;
	}

	@Override
	public List<Answer> getAnswer(String username) {
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Answer.class).add(Restrictions.ge("poster", username)));
	}

	@Override
	public void verifyAnswer(Long id, boolean verify) {
		Answer answer = getHibernateTemplate().get(Answer.class, id);
		if (verify) {
			answer.setVerify(1);
		}
		else {
			answer.setVerify(0);
		}
		getHibernateTemplate().update(answer);
	}

	@Override
	public void removeAnswer(Long id) {
		Answer answer = getAnswer(id);
		getHibernateTemplate().delete(answer);
	}

	
}
