package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdQuestion;

public interface QuestionDao {

	public int addQuestion(QdQuestion question);
	
	public List<QdQuestion> getQdQuestionList(int size, int page, String keyword);
	
	public QdQuestion getQuestion(int qid);
	
	public List<QdQuestion> getQuestionByUser(String username);
	
	public int updateQuestion(QdQuestion question);
	
	public int verifyQuestion(int qid, int verify);
	
	public int removeQuestion(int qid);
}
