package com.qingdy.service;

import java.io.Serializable;
import java.util.List;

public interface Manager {

	public Object getObject(Class clazz, Serializable id);
	
	public List getObjects(Class clazz);
	
	public void removeObject(Class clazz, Serializable id);
	
	public void saveObject(Object o);
}
