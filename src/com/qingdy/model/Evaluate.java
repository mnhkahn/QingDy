package com.qingdy.model;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evaluate extends BaseObject {
	@JsonProperty
	private Long id;
	@JsonProperty
	private Integer star;
	@JsonProperty
	private Date postDate;
	@JsonProperty
	private Blog blog;
	@JsonProperty
	private UserDetail poster;
	
	public Evaluate() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public UserDetail getPoster() {
		return poster;
	}

	public void setPoster(UserDetail poster) {
		this.poster = poster;
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
