package com.qingdy.dao;

public class SQLParameters {

	private int size;

	private int page;

	/**
	 * 数据库字段名
	 */
	private String field;
	/**
	 * 参数值 Object
	 */
	private String value;
	/**
	 * 数据库操作符 =、>=、<、like...
	 */
	private String operator;
	
	private String sidx;
	
	private String sord;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public SQLParameters() {
		
	}
	public void setField(String field) {
		this.field = field;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 构造方法
	 * 
	 * @param field
	 *            数据库字段名
	 * @param operator
	 *            数据库操作符 =、>=、<、like...
	 * @param value
	 *            参数值 Object
	 */
	public SQLParameters(int size, int page, String field, String operator, String value) {
		super();
		this.field = field;
		this.value = value;
		this.operator = operator;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public String getOperator() {
		return operator;
	}
}
