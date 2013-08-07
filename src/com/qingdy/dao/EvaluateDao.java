package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Evaluate;

public interface EvaluateDao extends DAO {

	public Evaluate getEvaluate(Long evaluateId);
	public void saveBlogEvaluate(Evaluate evaluate);
	public void removeEvaluate(Long evaluateId);
	public List getBlogEvaluate();	
}
