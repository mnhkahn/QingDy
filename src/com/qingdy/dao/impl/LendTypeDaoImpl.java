package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.LendTypeDao;
import com.qingdy.model.Clients;
import com.qingdy.model.Lendtype;

public class LendTypeDaoImpl extends CDaoImpl implements LendTypeDao {

	@Override
	public List<Lendtype> getLendtype() {
		List<Lendtype> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.lendtype";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Lendtype lendtype = new Lendtype();
				lendtype.setLtid(rs.getInt("ltid"));
				lendtype.setLendtype(rs.getString("lendtype"));
				lendtype.setParentid(rs.getInt("parentid"));
				list.add(lendtype);
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
