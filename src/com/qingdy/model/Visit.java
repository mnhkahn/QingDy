package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Visit {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("oid")
	private Long oid;
	@JsonProperty("type")
	private Integer type;
	@JsonProperty("user")
	private UserDetail user;
	@JsonProperty("date")
	private Date date;
	@JsonProperty("ip")
	private String ip;

	public Visit() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public UserDetail getUser() {
		return user;
	}

	public void setUser(UserDetail user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


}
