package com.qingdy.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.QuestionDao;
import com.qingdy.model.Question;

@Service("questionDao")
public class QuestionDaoHibernate extends BaseDaoHibernate implements QuestionDao {

	@Override
	public void saveQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getQuestion(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestion(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyQuestion(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeQuestion(Long id) {
		// TODO Auto-generated method stub
		
	}

}
