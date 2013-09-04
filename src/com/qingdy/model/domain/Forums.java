package com.qingdy.model.domain;

import java.util.List;
import java.util.Set;

import com.qingdy.model.Timeline;

public class Forums {
	
	private Long mallCount = new Long(0);
	
	private Long specialistCount = new Long(0);
	
	private Long transactionCount = new Long(0);
	
	private List<Timeline> timelines = null;
	
	public Forums() {
		
	}

	public Long getMallCount() {
		return mallCount;
	}

	public void setMallCount(Long mallCount) {
		this.mallCount = mallCount;
	}

	public Long getSpecialistCount() {
		return specialistCount;
	}

	public void setSpecialistCount(Long specialistCount) {
		this.specialistCount = specialistCount;
	}

	public Long getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(Long transactionCount) {
		this.transactionCount = transactionCount;
	}

	public List<Timeline> getTimelines() {
		return timelines;
	}

	public void setTimelines(List<Timeline> timelines) {
		this.timelines = timelines;
	}


	
}
