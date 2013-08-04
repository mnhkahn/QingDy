package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.QdLink;

public interface LinkDao {

	public int addLink(QdLink link);
	
	public List<QdLink> getLinkList(int size, int page);
	
	public int updateLink(QdLink link);
	
	public int removeLink(int lid);
}
