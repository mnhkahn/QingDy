package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("content")
	private String content;
	@JsonProperty("poster")
	private UserDetail poster;
	@JsonProperty("classes")
	private String classes;
	@JsonProperty("postDate")
	private Date postDate;
	
	public News() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDetail getPoster() {
		return poster;
	}

	public void setPoster(UserDetail poster) {
		this.poster = poster;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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
