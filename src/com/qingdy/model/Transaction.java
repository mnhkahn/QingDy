package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction extends BaseObject {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("loaner")
	private UserDetail loaner;
	@JsonProperty("lender")
	private UserDetail lender;
	@JsonProperty("title")
	private String title;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("comments")
	private String comments;
	@JsonProperty("frontcover")
	private String frontcover;
	@JsonProperty("verify")
	private Integer verify = -1;
	
	public Transaction() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDetail getLoaner() {
		return loaner;
	}

	public void setLoaner(UserDetail loaner) {
		this.loaner = loaner;
	}

	public UserDetail getLender() {
		return lender;
	}

	public void setLender(UserDetail lender) {
		this.lender = lender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
