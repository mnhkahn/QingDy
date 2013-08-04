package com.qingdy.model;

import java.util.LinkedList;
import java.util.List;

public class Row {
	
	private int id;
	
	private List cell = new LinkedList();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getCell() {
		return cell;
	}

	public void setCell(List cell) {
		this.cell = cell;
	}

}
