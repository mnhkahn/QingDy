package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Answer;

public interface AnswerDao {
	public List<Answer> getAnswers(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);

	public void saveAnswer(Answer answer);
	
	// Get answers of question
	public List<Answer> getAnswers(Long qid);
	
	public Answer getAnswer(Long id);
	
	// Get answers of user
	public List<Answer> getAnswer(String username);
	
	public void verifyAnswer(Long id, boolean verify);
	
	public void removeAnswer(Long id);
	
}
