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
	@JsonProperty("specialty")
	private String classes;

	public Long getScores() {
		return scores;
	}

	public void setScores(Long scores) {
		this.scores = scores;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

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
	
}
