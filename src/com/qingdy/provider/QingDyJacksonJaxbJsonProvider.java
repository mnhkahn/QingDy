package com.qingdy.provider;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

public class QingDyJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); 
	
	public QingDyJacksonJaxbJsonProvider() {
		super();
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.getSerializationConfig().setDateFormat(sdf);
		this.setMapper(objectMapper);
	}

}