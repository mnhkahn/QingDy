package com.qingdy.domain;
import java.sql.Timestamp;
import java.util.Date;


public class QdTransaction {
	private Integer tid;
	private Integer loanerid;
	private Integer lenderid;
	private String title;
	private Timestamp postdate;
	private Integer sort;
	private String comments;
	private String frontcover;
	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	public Integer getLoanerid() {
		return loanerid;
	}

	public void setLoanerid(Integer loanerid) {
		this.loanerid = loanerid;
	}
	
	public Integer getLenderid() {
		return lenderid;
	}

	public void setLenderid(Integer lenderid) {
		this.lenderid = lenderid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getFrontcover() {
		return frontcover;
	}

	public void setFrontcover(String frontcover) {
		this.frontcover = frontcover;
	}
}
