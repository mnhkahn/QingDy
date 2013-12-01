package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.qingdy.provider.CustomJsonDateDeserializer;

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
	@JsonProperty("city")
	private String city;
	@JsonProperty("browser")
	private String browser;
	@JsonProperty("resolution")
	private String resolution;
	@JsonProperty("os")
	private String os;
	@JsonProperty("fromSource")
	private String fromSource;
	@JsonProperty("isp")
	private String isp;

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

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

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public Date getEndDate() {
		return endDate;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrower(String browser) {
		this.browser = browser;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


}
