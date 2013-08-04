package com.qingdy.model;


public class Areas {
	private Integer areaid;
	private String name;
	private String joinname;
	private Integer parentid;
	private Integer vieworder;
	
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getJoinname() {
		return joinname;
	}

	public void setJoinname(String joinname) {
		this.joinname = joinname;
	}
	
	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	
	public Integer getVieworder() {
		return vieworder;
	}

	public void setVieworder(Integer vieworder) {
		this.vieworder = vieworder;
	}
}
