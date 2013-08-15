package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Question;

public interface QuestionDao {

	public void saveQuestion(Question question);
	
	public List<Question> getQuestions();
	
	public Question getQuestion(Long id);
	
	public List<Question> getQuestion(String username);

	public void verifyQuestion(Long id, boolean verify);
	
	public void removeQuestion(Long id);
}
