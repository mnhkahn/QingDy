package com.qingdy.domain;
import java.sql.Timestamp;


public class QdLoan {
	private Integer lid;
	private Integer uid;
	private float amount;
	private Timestamp deadline;
	private String usesofloan;
	private Integer lendtype;
	private Integer haspawn;
	private String pawn;
	private String title;
	private String introduce;
	private Integer verify;
	private Timestamp postdate;
	private Integer location;
	private Integer rNum;
	
	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	
	public String getUsesofloan() {
		return usesofloan;
	}

	public void setUsesofloan(String usesofloan) {
		this.usesofloan = usesofloan;
	}
	
	public Integer getLendtype() {
		return lendtype;
	}

	public void setLendtype(Integer lendtype) {
		this.lendtype = lendtype;
	}
	
	public Integer getHaspawn() {
		return haspawn;
	}

	public void setHaspawn(Integer haspawn) {
		this.haspawn = haspawn;
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
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}
	
	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}
	
	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}
	
	public Integer getRNum() {
		return rNum;
	}

	public void setRNum(Integer rNum) {
		this.rNum = rNum;
	}
}
