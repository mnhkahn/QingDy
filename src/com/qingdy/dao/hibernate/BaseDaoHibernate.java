package com.qingdy.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qingdy.dao.DAO;

public class BaseDaoHibernate extends HibernateDaoSupport implements DAO {

//	protected final Log log = LogFactory.getLogger(getClass());
	@Override
	public List getObjects(Class cls) {
		return getHibernateTemplate().loadAll(cls);
	}

	@Override
	public Object getObject(Class cls, Serializable id) {
		Object o = getHibernateTemplate().get(cls, id);
		if(o == null) {
			throw new ObjectRetrievalFailureException(cls, id);
		}
		return o;
	}

	@Override
	public void saveObject(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	@Override
	public void removeObject(Class cls, Serializable id) {
		getHibernateTemplate().delete(getObject(cls, id));
	}
}
