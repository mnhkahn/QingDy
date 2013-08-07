package com.qingdy.service;

import java.io.Serializable;

import java.util.List;

import com.mysql.jdbc.log.LogFactory;
import com.qingdy.dao.DAO;

public class BaseManager/* implements Manager*/ {
	protected DAO dao = null;
	//protected final Log log = LogFactory.getLog(getClass());
	public void setDAO(DAO dao) {
		this.dao = dao;
	}
	public Object getObject(Class clazz, Serializable id) {
		return dao.getObject(clazz, id);
	}
	public List getObjects(Class clazz) {
		return dao.getObjects(clazz);
	}
	public void removeObject(Class clazz, Serializable id) {
		dao.removeObject(clazz, id);
	}
	public void saveObject(Object o) {
		dao.saveObject(o);
	}
}
