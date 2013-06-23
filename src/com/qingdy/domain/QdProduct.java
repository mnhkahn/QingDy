package com.qingdy.domain;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Date;


public class QdProduct {
	private Integer pid;
	private Integer uid;
	private Integer mid;
	private String pname;
	private Integer max;
	private Integer min;
	private Integer ratetype;
	private float rate;
	private Timestamp deadline;
	private Integer clientlocation;
	private Integer client;
	private Integer repaymentmethod;
	private Integer ptype;
	private Integer usesofloan;
	private String introduce;
	private String processes;
	private String application;
	private String faq;
	private Integer rNum;
	private Timestamp postdate;
	private Integer verify;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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
	
	public Integer getRatetype() {
		return ratetype;
	}

	public void setRatetype(Integer ratetype) {
		this.ratetype = ratetype;
	}
	
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	
	public int getClientlocation() {
		return clientlocation;
	}

	public void setClientlocation(Integer clientlocation) {
		this.clientlocation = clientlocation;
	}
	
	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}
	
	public int getRepaymentmethod() {
		return repaymentmethod;
	}

	public void setRepaymentmethod(Integer repaymentmethod) {
		this.repaymentmethod = repaymentmethod;
	}
	
	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}
	
	public int getUsesofloan() {
		return usesofloan;
	}

	public void setUsesofloan(Integer usesofloan) {
		this.usesofloan = usesofloan;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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
	
	public Integer getRNum() {
		return rNum;
	}

	public void setRNum(Integer rNum) {
		this.rNum = rNum;
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
}
