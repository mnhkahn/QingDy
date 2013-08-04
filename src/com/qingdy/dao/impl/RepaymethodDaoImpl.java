package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.RepaymethodDao;
import com.qingdy.model.Repaymethod;

public class RepaymethodDaoImpl extends CDaoImpl implements RepaymethodDao {

	@Override
	public List<Repaymethod> getRepaymethods() {
		List<Repaymethod> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.repaymethod";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Repaymethod repaymethod = new Repaymethod();
				repaymethod.setRid(rs.getInt("rid"));
				repaymethod.setRepaymethodcol(rs.getString("repaymethodcol"));
				list.add(repaymethod);
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
