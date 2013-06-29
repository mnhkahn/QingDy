package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.RatetypeDao;
import com.qingdy.domain.Ratetype;

public class RatetypeDaoImpl extends CDaoImpl implements RatetypeDao {

	@Override
	public List<Ratetype> getRatetypes() {
		List<Ratetype> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.ratetype";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Ratetype ratetype = new Ratetype();
				ratetype.setRid(rs.getInt("rid"));
				ratetype.setRatetype(rs.getString("ratetype"));
				list.add(ratetype);
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
