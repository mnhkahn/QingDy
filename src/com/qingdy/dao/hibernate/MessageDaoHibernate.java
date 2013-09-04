package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.qingdy.common.Constant;
import com.qingdy.dao.MessageDao;
import com.qingdy.model.Message;
import com.qingdy.model.UserDetail;
@Service("messageDao")
public class MessageDaoHibernate extends BaseDaoHibernate implements MessageDao {

	@Override
	public void saveMessage(Message message) {
		getHibernateTemplate().saveOrUpdate(message);
	}

	@Override
	public void removeMessage(Long id) {
		Message message = getHibernateTemplate().get(Message.class, id);
		getHibernateTemplate().delete(message);
	}

	@Override
	public List<Message> getSendMessages(String username) {
		UserDetail sender = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Message.class).add(Restrictions.eq("sender", sender)).addOrder(Order.desc("postDate")));
	}

	@Override
	public List<Message> getReceiveMessages(String username) {
		UserDetail receiver = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Message.class).add(Restrictions.eq("receiver", receiver)).addOrder(Order.desc("postDate")));
	}

	@Override
	public void readMessage(Long id) {
		Message message = getHibernateTemplate().get(Message.class, id);
		message.setIsReaded(Constant.READEN);
		saveMessage(message);
	}
	
	@Override
	public Integer getUnreadCount(String username) {
		System.out.println(username);
		UserDetail receiver = getHibernateTemplate().get(UserDetail.class, username);
		List<Message> list = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Message.class).add(Restrictions.eq("receiver", receiver)).add(Restrictions.eq("isReaded", Constant.UNREAD)));
		return list.size();
	}

}
