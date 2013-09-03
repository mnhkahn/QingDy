package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.qingdy.common.Constant;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message extends BaseObject {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("sender")
	private UserDetail sender;
	@JsonProperty("receiver")
	private UserDetail receiver;
	@JsonProperty("title")
	private String title;
	@JsonProperty("message")
	private String message;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("isReaded")
	private int isReaded = Constant.UNREAD;
	
	public Message() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDetail getSender() {
		return sender;
	}

	public void setSender(UserDetail sender) {
		this.sender = sender;
	}

	public UserDetail getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDetail receiver) {
		this.receiver = receiver;
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
