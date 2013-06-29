package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.UsesofloanDao;
import com.qingdy.domain.Usersofloan;

public class UsesofloanDaoImpl extends CDaoImpl implements UsesofloanDao{

	@Override
	public List<Usersofloan> getUsesofloans() {
		List<Usersofloan> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.usersofloan";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Usersofloan usersofloan = new Usersofloan();
				usersofloan.setUolid(rs.getInt("uolid"));
				usersofloan.setUsesofloan(rs.getString("usesofloan"));
				usersofloan.setParentid(rs.getInt("parentid"));
				list.add(usersofloan);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return list;
	}

}
