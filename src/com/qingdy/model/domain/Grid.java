package com.qingdy.model.domain;

import org.codehaus.jackson.annotate.JsonProperty;

public class Grid {
	@JsonProperty("records")
	public Integer records;
	@JsonProperty("total")
	public Integer total;
	@JsonProperty("page")
	public Integer page;
	@JsonProperty("rows")
	public Object rows;
	
	public Grid() {
		
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	
}
