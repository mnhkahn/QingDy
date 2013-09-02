package com.qingdy.model;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Blog extends BaseObject {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("content")
	private String content;
	@JsonProperty("poster")
	private UserDetail poster;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("category")
	private String category;
	@JsonProperty("evaluate")
	private Set evaluate;
	@JsonProperty("verify")
	private int verify = -1;
	
	public Blog() {
		
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Set evaluate) {
		this.evaluate = evaluate;
	}

	public int isVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("title", this.title)
				.append("postDate", this.postDate).append("content", this.content)
				.append("poster", this.poster).append("category", this.category).toString();
//				.append("evaluate", evaluate).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Blog)) {
			return false;
		}
		Blog rhs = (Blog)o;
		return this.poster.equals(rhs.getPoster())
				&& this.postDate.equals(rhs.getPostDate());
	}

	@Override
	public int hashCode() {
		//return this.poster.hashCode() + this.postDate.hashCode();
		return 0;
	}	
}
