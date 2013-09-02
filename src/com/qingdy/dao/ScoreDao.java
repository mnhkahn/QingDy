package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Score;
import com.qingdy.model.UserDetail;

public interface ScoreDao extends DAO {

	public void addScore(Score score);
	
	public Long getSpecialistCount();
	
}
