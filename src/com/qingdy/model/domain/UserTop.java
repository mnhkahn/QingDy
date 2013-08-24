package com.qingdy.model.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.qingdy.model.BaseObject;
import com.qingdy.model.Message;
import com.qingdy.model.UserDetail;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTop extends BaseObject {

	@JsonProperty("user")
	private UserDetail user;
	@JsonProperty("message")
	private Message message;
	
	public UserTop() {
		
	}

	public UserDetail getUser() {
		return user;
	}

	public void setUser(UserDetail user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

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
