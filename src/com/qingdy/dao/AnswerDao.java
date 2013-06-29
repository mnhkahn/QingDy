package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdAnswer;

public interface AnswerDao {

	public int addAnswer(QdAnswer answer);
	
	public List<QdAnswer> getAnswerByQuestion(int qid);
	
	public List<QdAnswer> getAnswerList(int size, int page, String keyword);
	
	public QdAnswer getAnswer(int aid);
	
	public List<QdAnswer> getAnswerByUser(String username);
	
	public int updateAnswer(QdAnswer answer);
	
	public int verifyAnswer(int aid, int verify);
	
	public int removeAnswer(int aid);
	
}
