package com.qingdy.model;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


public class Mall extends BaseObject{
	@JsonProperty("id")
	private Long id;
	@JsonProperty("poster")
	private UserDetail poster;
	@JsonProperty("content")
	private String content;
	@JsonProperty("announcement")
	private String announcement;
	@JsonProperty("experience")
	private Integer experience;
	@JsonProperty("cType")
	private String cType;
	@JsonProperty("cName")
	private String cName;
	@JsonProperty("cPhoneNumber")
	private String cPhoneNumber;
	@JsonProperty("position")
	private String position;
	@JsonProperty("site")
	private String site;
	@JsonProperty("email")
	private String email;
	@JsonProperty("address")
	private String address;
	@JsonProperty("fax")
	private String fax;
	@JsonProperty("postCode")
	private String postCode;
	@JsonProperty("clientLocation")
	private String clientLocation;
	@JsonProperty("usesofloan")
	private String usesofloan;
	@JsonProperty("speciality")
	private String speciality;
	@JsonProperty("clients")
	private String clients;
	@JsonProperty("lendType")
	private String lendType;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("verify")
	private Integer verify;
	
	public Mall() {
		
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



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getAnnouncement() {
		return announcement;
	}



	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}



	public Integer getExperience() {
		return experience;
	}



	public void setExperience(Integer experience) {
		this.experience = experience;
	}



	public String getcType() {
		return cType;
	}



	public void setcType(String cType) {
		this.cType = cType;
	}



	public String getcName() {
		return cName;
	}



	public void setcName(String cName) {
		this.cName = cName;
	}



	public String getcPhoneNumber() {
		return cPhoneNumber;
	}



	public void setcPhoneNumber(String cPhoneNumber) {
		this.cPhoneNumber = cPhoneNumber;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getSite() {
		return site;
	}



	public void setSite(String site) {
		this.site = site;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getFax() {
		return fax;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}



	public String getPostCode() {
		return postCode;
	}



	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}



	public String getClientLocation() {
		return clientLocation;
	}



	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
	}



	public String getUsesofloan() {
		return usesofloan;
	}



	public void setUsesofloan(String usesofloan) {
		this.usesofloan = usesofloan;
	}



	public String getSpeciality() {
		return speciality;
	}



	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}



	public String getClients() {
		return clients;
	}



	public void setClients(String clients) {
		this.clients = clients;
	}



	public String getLendType() {
		return lendType;
	}



	public void setLendType(String lendType) {
		this.lendType = lendType;
	}



	public Date getPostDate() {
		return postDate;
	}



	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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
