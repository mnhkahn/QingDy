package com.qingdy.model;
import java.sql.Timestamp;
import java.util.Date;


public class QdQuestion {
	private Integer qid;
	private Integer uid;
	private String title;
	private String question;
	private Integer classes;
	private Timestamp postdate;
	private Integer iscompleted;
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Integer getClasses() {
		return classes;
	}

	public void setClasses(Integer classes) {
		this.classes = classes;
	}
	
	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}
	
	public Integer getIscompleted() {
		return iscompleted;
	}

	public void setIscompleted(Integer iscompleted) {
		this.iscompleted = iscompleted;
	}
}
