package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Visit;

public interface VisitDao extends DAO {

	public void addVisit(Visit visit);
	
	public List<Visit> getVisits(int type, Long id, int size, int page);
	
	public List<Visit> getVisits(String username);
}
