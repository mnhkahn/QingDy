package com.qingdy.model.domain;

import java.util.Date;

public class VisitDate {

	Long oid;
	Integer type;
	Integer count;
	String date;
	
	public VisitDate(Long oid, Integer type, Integer count, String date) {
		this.oid = oid;
		this.type = type;
		this.count = count;
		this.date = date;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
