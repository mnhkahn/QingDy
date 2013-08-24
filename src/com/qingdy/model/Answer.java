package com.qingdy.model;
import java.util.Date;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;


public class Answer extends BaseObject{
	@JsonProperty("id")
	private Long id;
	@JsonProperty("poster")
	private UserDetail poster;
	@JsonProperty("answer")
	private String answer;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("question")
	private Question question;
	@JsonProperty("verify")
	private Integer verify = -1;
	
	public Answer() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDetail getPoster() {
		return poster;
	}
	public void setPoster(UserDetail poster) {
		this.poster = poster;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
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
