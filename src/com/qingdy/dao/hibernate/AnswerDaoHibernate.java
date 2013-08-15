package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.AnswerDao;
import com.qingdy.model.Answer;

@Service("answerDao")
public class AnswerDaoHibernate extends BaseDaoHibernate implements AnswerDao {

	@Override
	public List<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAnswer(Answer answer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Answer> getAnswers(Long qid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer getAnswer(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getAnswer(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyAnswer(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAnswer(Long id) {
		// TODO Auto-generated method stub
		
	}

	
}
