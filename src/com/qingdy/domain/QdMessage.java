package com.qingdy.domain;
import java.sql.Timestamp;
import java.util.Date;


public class QdMessage {
	private Integer mid;
	private Integer fromuid;
	private Integer touid;
	private String title;
	private String message;
	private Timestamp postdate;
	private Integer isreaded;
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
	public Integer getFromuid() {
		return fromuid;
	}

	public void setFromuid(Integer fromuid) {
		this.fromuid = fromuid;
	}
	
	public Integer getTouid() {
		return touid;
	}

	public void setTouid(Integer touid) {
		this.touid = touid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}
	
	public Integer getIsreaded() {
		return isreaded;
	}

	public void setIsreaded(Integer isreaded) {
		this.isreaded = isreaded;
	}
}
