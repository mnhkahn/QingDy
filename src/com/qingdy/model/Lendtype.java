package com.qingdy.model;


public class Lendtype {
	private Integer ltid;
	private String lendtype;
	private Integer parentid;
	
	public Integer getLtid() {
		return ltid;
	}

	public void setLtid(Integer ltid) {
		this.ltid = ltid;
	}
	
	public String getLendtype() {
		return lendtype;
	}

	public void setLendtype(String lendtype) {
		this.lendtype = lendtype;
	}
	
	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}
