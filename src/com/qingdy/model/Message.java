package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message extends BaseObject {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("from")
	private UserDetail from;
	@JsonProperty("to")
	private UserDetail to;
	@JsonProperty("title")
	private String title;
	@JsonProperty("message")
	private String message;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("isReaded")
	private int isReaded;
	
	public Message() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDetail getFrom() {
		return from;
	}

	public void setFrom(UserDetail from) {
		this.from = from;
	}

	public UserDetail getTo() {
		return to;
	}

	public void setTo(UserDetail to) {
		this.to = to;
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(int isReaded) {
		this.isReaded = isReaded;
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
