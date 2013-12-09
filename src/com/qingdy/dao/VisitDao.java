package com.qingdy.dao;

import java.util.Date;
import java.util.List;

import com.qingdy.model.Visit;
import com.qingdy.model.domain.VisitDate;

public interface VisitDao extends DAO {

	public void addVisit(Visit visit);
	
	public List<Visit> getVisits(int type, Long id, int size, int page);
	
	public List<VisitDate> getVisits(int type, Long id, Date startTime, Date endTime);
	
	public int getVisits(int type, Long id);
	
	public List<Visit> getUserVisits(String username, int size, int page);
}
