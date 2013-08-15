package com.qingdy.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserTop extends BaseObject {
	@JsonProperty("username")
	private String username;
	@JsonProperty("name")
	private String name;
	@JsonProperty("avatar")
	private String avatar;
	@JsonProperty("message")
	private Integer message;	
	
	public UserTop() {
		
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public Integer getMessage() {
		return message;
	}


	public void setMessage(Integer message) {
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
