package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.ClientsDao;
import com.qingdy.model.Clients;

public class ClientsDaoImpl extends CDaoImpl implements ClientsDao {

	@Override
	public List<Clients> getClients() {
		List<Clients> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.clients";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Clients clients = new Clients();
				clients.setCtid(rs.getInt("ctid"));
				clients.setCtype(rs.getString("ctype"));
				list.add(clients);
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
