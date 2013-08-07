package com.qingdy.model;
import java.sql.Timestamp;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Blog extends BaseObject {
	private Long id;
	private String title;
	private String content;
	private User poster;
	private Timestamp postDate;
	private String category;
	private Set evaluate;
	
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

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
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
