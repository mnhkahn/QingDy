package com.qingdy.utility;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qingdy.common.cGenerateBean;
import com.qingdy.common.cJDBCUtilsSingleton;

public class cGenerateDomain {

	/**
	 * @param args
	 */	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection con = cJDBCUtilsSingleton.getInstance().getConnection();
		DatabaseMetaData databaseMetaData = con.getMetaData();
		String[] tableType = { "TABLE" };
		ResultSet rs = databaseMetaData.getTables(null, null, "%",tableType);
		cGenerateBean d = new cGenerateBean();
		while(rs.next()){
			String tableName = rs.getString(3).toString();
			d.tableToBean(con,tableName);
		}
		System.out.println("Generate domain success!");
	}

}
