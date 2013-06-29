package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.BankDao;
import com.qingdy.domain.QdBanks;

public class BankDaoImpl extends CDaoImpl implements BankDao {

	@Override
	public int addBank(QdBanks bank) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_banks(name,business,logo) values(?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bank.getName());
			ps.setString(2, bank.getBusiness());
			ps.setString(3, bank.getLogo());			
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return 1;
	}

	@Override
	public List<QdBanks> getBankList(int size, int page) {
		List<QdBanks> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_banks limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, (page - 1) * size);
			ps.setInt(2, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdBanks banks = new QdBanks();
				banks.setBid(rs.getInt("bid"));
				banks.setBusiness(rs.getString("business"));
				banks.setName(rs.getString("name"));
				banks.setLogo(rs.getString("logo"));

				list.add(banks);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public QdBanks getBank(int bid) {
		QdBanks banks = null;
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_banks where bid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				banks = new QdBanks();
				banks.setBid(rs.getInt("bid"));
				banks.setBusiness(rs.getString("business"));
				banks.setName(rs.getString("name"));
				banks.setLogo(rs.getString("logo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return banks;
	}

	@Override
	public int updateBank(QdBanks bank) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_banks set name=?, business=?, logo=? where bid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bank.getName());
			ps.setString(2, bank.getBusiness());
			ps.setString(3, bank.getLogo());
			ps.setInt(4, bank.getBid());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return 1;
	}

	@Override
	public int removeBank(int bid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_banks where bid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return 1;
	}

}
