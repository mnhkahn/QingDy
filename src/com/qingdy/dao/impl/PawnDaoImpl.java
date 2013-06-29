package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.PawnDao;
import com.qingdy.domain.Pawn;

public class PawnDaoImpl extends CDaoImpl implements PawnDao {

	@Override
	public List<Pawn> getPawn() {
		List<Pawn> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.pawn";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Pawn pawn = new Pawn();
				pawn.setPid(rs.getInt("pid"));
				pawn.setPawn(rs.getString("pawn"));
				list.add(pawn);
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
