package com.qingdy.model.domain;

import java.util.Date;

public class VisitDayStat {

	private Integer type;
	private Long id;
	private VisitDay [] visitDays = null;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VisitDay[] getVisitDays() {
		return visitDays;
	}
	public void setVisitDays(VisitDay[] visitDays) {
		this.visitDays = visitDays;
	}
}

class VisitDay {
	private Date data;
	private Integer count;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}