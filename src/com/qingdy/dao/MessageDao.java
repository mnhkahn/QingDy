package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Message;

public interface MessageDao extends DAO {

	public void saveMessage(Message message);
	
	public void removeMessage(Long id);
	
	public void readMessage(Long id);
	
	public List<Message> getSendMessages(String username);
	
	public List<Message> getReceiveMessages(String username);
	
	public Integer getUnreadCount(String username);
}
