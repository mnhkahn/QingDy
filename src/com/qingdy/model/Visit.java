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
	@JsonProperty("ip")
	private String ip;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("visitCity")
	private String visitCity;
	@JsonProperty("visitBrowerType")
	private Integer visitBrowerType;
	@JsonProperty("visitResolution")
	private String visitResolution;
	@JsonProperty("visitOS")
	private String visitOS;
	@JsonProperty("fromSource")
	private String fromSource;

	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVisitCity() {
		return visitCity;
	}

	public void setVisitCity(String visitCity) {
		this.visitCity = visitCity;
	}

	public Integer getVisitBrowerType() {
		return visitBrowerType;
	}

	public void setVisitBrowerType(Integer visitBrowerType) {
		this.visitBrowerType = visitBrowerType;
	}

	public String getVisitResolution() {
		return visitResolution;
	}

	public void setVisitResolution(String visitResolution) {
		this.visitResolution = visitResolution;
	}

	public String getVisitOS() {
		return visitOS;
	}

	public void setVisitOS(String visitOS) {
		this.visitOS = visitOS;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


}
