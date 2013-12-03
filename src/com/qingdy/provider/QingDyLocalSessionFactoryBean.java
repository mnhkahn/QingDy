package com.qingdy.provider;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class QingDyLocalSessionFactoryBean extends LocalSessionFactoryBean {

	@Override
	protected void postProcessConfiguration(Configuration config)
			throws HibernateException {
		// TODO Auto-generated method stub
//		super.postProcessConfiguration(config);
		config.addSqlFunction("group_concat", new StandardSQLFunction("group_concat", Hibernate.STRING));
	}

}
