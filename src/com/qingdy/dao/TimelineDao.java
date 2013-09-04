package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Timeline;

public interface TimelineDao extends DAO {

	public List<Timeline> getTimelines(int size, int page);
	
	public List<Timeline> geTimelinesByUser(String username, int size, int page);
}
