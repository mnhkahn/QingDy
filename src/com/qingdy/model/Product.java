package com.qingdy.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseObject{
	@JsonProperty("id")
	private Long id;
	@JsonProperty("poster")
	private UserDetail poster;
	@JsonProperty("mall")
	private Mall mall;
	@JsonProperty("pName")
	private String pName;
	@JsonProperty("max")
	private Integer max;
	@JsonProperty("min")
	private Integer min;
	@JsonProperty("rateType")
	private String rateType;
	@JsonProperty("rate")
	private float rate;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endTime")
	private Date endTIme;
	@JsonProperty("clientLocation")
	private String clientLocation;
	@JsonProperty("client")
	private String client;
	@JsonProperty("repaymentMethod")
	private String repaymentMethod;
	@JsonProperty("pType")
	private String pType;
	@JsonProperty("usesofloan")
	private String usesofloan;
	@JsonProperty("content")
	private String content;
	@JsonProperty("processes")
	private String processes;
	@JsonProperty("application")
	private String application;
	@JsonProperty("faq")
	private String faq;
	@JsonProperty("postDate")
	private Date postDate;
	@JsonProperty("verify")
	private Integer verify = -1;
	
	public Product() {
		
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



	public Mall getMall() {
		return mall;
	}



	public void setMall(Mall mall) {
		this.mall = mall;
	}



	public String getpName() {
		return pName;
	}



	public void setpName(String pName) {
		this.pName = pName;
	}



	public Integer getMax() {
		return max;
	}



	public void setMax(Integer max) {
		this.max = max;
	}



	public Integer getMin() {
		return min;
	}



	public void setMin(Integer min) {
		this.min = min;
	}



	public String getRateType() {
		return rateType;
	}



	public void setRateType(String rateType) {
		this.rateType = rateType;
	}



	public float getRate() {
		return rate;
	}



	public void setRate(float rate) {
		this.rate = rate;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTIme() {
		return endTIme;
	}



	public void setEndTIme(Date endTIme) {
		this.endTIme = endTIme;
	}



	public String getClientLocation() {
		return clientLocation;
	}



	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
	}



	public String getClient() {
		return client;
	}



	public void setClient(String client) {
		this.client = client;
	}



	public String getRepaymentMethod() {
		return repaymentMethod;
	}



	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}



	public String getpType() {
		return pType;
	}



	public void setpType(String pType) {
		this.pType = pType;
	}



	public String getUsesofloan() {
		return usesofloan;
	}



	public void setUsesofloan(String usesofloan) {
		this.usesofloan = usesofloan;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getProcesses() {
		return processes;
	}



	public void setProcesses(String processes) {
		this.processes = processes;
	}



	public String getApplication() {
		return application;
	}



	public void setApplication(String application) {
		this.application = application;
	}



	public String getFaq() {
		return faq;
	}



	public void setFaq(String faq) {
		this.faq = faq;
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
