package com.qingdy.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class cJDBCUtilsSingleton {
	
	private String connStr = "jdbc:mysql://localhost:3306/QingDyDB?characterEncoding=utf8";
	private String userName = "qingdy";
	private String userPWD = "qingdy";
	
	private static cJDBCUtilsSingleton instance;
	
	protected cJDBCUtilsSingleton() {
		
	}
	
	public static cJDBCUtilsSingleton getInstance() {
		if (instance == null) {
			// Delay load and synchronized load
			synchronized (cJDBCUtilsSingleton.class) {
				if (instance == null)
					instance = new cJDBCUtilsSingleton();
			}
		}
		return instance;
	}
	
	static {
		// 1.Register driver for only once
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connStr, userName, userPWD);
	}
	
	// 6.Release resources
	public void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}

}