package com.qingdy.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO {
	public List getObjects(Class cls);
	public Object getObject(Class cls, Serializable id);
	public void saveObject(Object o);
	public void removeObject(Class cls, Serializable id);
}
