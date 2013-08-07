package com.fasterxml.jackson.datatype.hibernate4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
    	registerModule(new Hibernate4Module());
    }

	private void registerModule(Hibernate4Module hibernate4Module) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		// for Hibernate 4.x:
		mapper.registerModule(hibernate4Module);
	}
}
