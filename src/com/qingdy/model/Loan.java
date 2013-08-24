package com.qingdy.model;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


public class Loan extends BaseObject {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("poster")
	private UserDetail poster;
	@JsonProperty("amount")
	private float amount;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("usesofloan")
	private String usesofloan;
	@JsonProperty("lendType")
	private String lendType;
	@JsonProperty("hasPawn")
	private Integer hasPawn;
	@JsonProperty("pawn")
	private String pawn;
	@JsonProperty("title")
	private String title;
	@JsonProperty("content")
	private String content;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("location")
	private String location;
	@JsonProperty("verify")
	private Integer verify = -1;

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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUsesofloan() {
		return usesofloan;
	}
	public void setUsesofloan(String usesofloan) {
		this.usesofloan = usesofloan;
	}
	public String getLendType() {
		return lendType;
	}
	public void setLendType(String lendType) {
		this.lendType = lendType;
	}
	public Integer getHasPawn() {
		return hasPawn;
	}
	public void setHasPawn(Integer hasPawn) {
		this.hasPawn = hasPawn;
	}
	public String getPawn() {
		return pawn;
	}
	public void setPawn(String pawn) {
		this.pawn = pawn;
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
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
