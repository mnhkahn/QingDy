package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Score;
import com.qingdy.model.UserDetail;
import com.qingdy.model.domain.Specialist;

public interface SpecialistDao extends DAO {

	public List<Score> getSpecialists(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public List<Specialist> getSpecialists(int size, int page, String field[], String value[], String operator[], String sidx, String sord, boolean verify);
}
