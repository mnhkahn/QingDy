package com.qingdy.dao;

import java.util.List;

import com.qingdy.domain.QdMessage;

public interface MessageDao {

	public List<QdMessage> getMessageList(int size, int page, int touid);
	
	public int getUnreadCount(int touid);
	
	public int addMessage(QdMessage message);
}
