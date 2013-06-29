package com.qingdy.domain;

import java.sql.Timestamp;

public class Mall {

	private Integer mid;
	private String userFullName;
	private String introduce;
	private String announcement;
	private Integer lendedyears;
	private String ctype;
	private String cname;
	private String cphonenumber;
	private String cposition;
	private String csite;
	private String cemail;
	private String caddress;
	private String cfax;
	private String cpostcode;
	private String clientlocation;
	private String usesofloan;
	private String speciality;
	private String clients;
	private String lendtype;
	private Timestamp postdate;
	private Integer verify;
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public void setClientlocation(String clientlocation) {
		this.clientlocation = clientlocation;
	}

	public void setUsesofloan(String usesofloan) {
		this.usesofloan = usesofloan;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public void setLendtype(String lendtype) {
		this.lendtype = lendtype;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	
	public int getLendedyears() {
		return lendedyears;
	}

	public void setLendedyears(Integer lendedyears) {
		this.lendedyears = lendedyears;
	}
	
	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getCphonenumber() {
		return cphonenumber;
	}

	public void setCphonenumber(String cphonenumber) {
		this.cphonenumber = cphonenumber;
	}
	
	public String getCposition() {
		return cposition;
	}

	public void setCposition(String cposition) {
		this.cposition = cposition;
	}
	
	public String getCsite() {
		return csite;
	}

	public void setCsite(String csite) {
		this.csite = csite;
	}
	
	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	
	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	
	public String getCfax() {
		return cfax;
	}

	public void setCfax(String cfax) {
		this.cfax = cfax;
	}
	
	public String getCpostcode() {
		return cpostcode;
	}

	public void setCpostcode(String cpostcode) {
		this.cpostcode = cpostcode;
	}
	
	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}
	
	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}
	
	public String getClientlocation() {
		return clientlocation;
	}

	public String getUsesofloan() {
		return usesofloan;
	}

	public String getSpeciality() {
		return speciality;
	}

	public String getClients() {
		return clients;
	}

	public String getLendtype() {
		return lendtype;
	}
	
}
