package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Visit;

public interface VisitDao extends DAO {

	public void addVisit(Visit visit);
	
	public List<Visit> getVisits(int type, Long id, int size, int page);
	
	public int getVisits(int type, Long id);
	
	public List<Visit> getUserVisits(String username, int size, int page);
}
