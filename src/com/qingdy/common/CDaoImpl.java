package com.qingdy.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CDaoImpl {
	
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	protected String sql = null;
}
