package com.qingdy.model.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import com.qingdy.model.UserDetail;

public class Specialist {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("user")
	private UserDetail user;
	@JsonProperty("scores")
	private Long scores;

	public Specialist() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDetail getUser() {
		return user;
	}

	public void setUser(UserDetail user) {
		this.user = user;
	}

	public Long getScore() {
		return scores;
	}

	public void setScore(Long scores) {
		this.scores = scores;
	}
	
}
