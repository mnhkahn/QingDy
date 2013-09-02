package com.qingdy.model.domain;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserTop {

	@JsonProperty("name")
	private String name;
	@JsonProperty("avatar")
	private String avatar;
	@JsonProperty("message")
	private Integer message;
	@JsonProperty("groupId")
	private Integer groupId;
	
	public UserTop() {
		
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


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
