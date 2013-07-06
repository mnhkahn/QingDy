package com.qingdy.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qingdy.dao.SQLParameters;

public class CDaoImpl {

	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	protected String sql = null;

	private String templet = " AND %s %s ?";
	private String baseSql;
	protected SQLParameters parameter = new SQLParameters();

	public CDaoImpl() {
	}

	/**
	 * baseSql需要带有where条件
	 * 
	 * @param baseSql
	 */
	public void setBaseSql(String baseSql) {
		this.baseSql = baseSql;
	}

	public String generateSql() {
		StringBuffer sb = new StringBuffer(baseSql);
		System.out.println(parameter.getField() == null);
		System.out.println(parameter.getField() + parameter.getOperator() + parameter.getValue());
		if (parameter.getField() != null) {
			sb.append(String.format(templet, parameter.getField(), parameter.getOperator()));
		}
		sb.append(" order by " + parameter.getSidx() + " " + parameter.getSord() +  " limit ?,?");

		System.out.println(sb.toString());
		return sb.toString();
	}

	public void fillPreparedStatement(PreparedStatement pst)
			throws SQLException {
		// 这里使用SetObjcet的缺点是失去了类型的验证功能，如果大家不嫌麻烦，可以判断，根据类型，set不同的类型
		int count = 1;

		if (parameter.getField() != null)
			pst.setObject(count++, parameter.getValue());
		pst.setObject(count++, (parameter.getPage() - 1) * parameter.getSize());
		pst.setObject(count++, parameter.getSize());
	}
}
