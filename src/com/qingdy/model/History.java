package com.qingdy.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class History extends BaseObject {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("type")
	private Integer type;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
