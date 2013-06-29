package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.LoanDao;
import com.qingdy.domain.QdLoan;
import com.qingdy.domain.QdTransaction;

public class LoanDaoImpl extends CDaoImpl implements LoanDao {

	@Override
	public int addLoan(QdLoan loan) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_loan(uid,title,amount,deadline,usesofloan,lendtype,haspawn,pawn,introduce,location,postdate) values(?,?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loan.getUid());
			ps.setString(2, loan.getTitle());
			ps.setFloat(3, loan.getAmount());
			ps.setTimestamp(4, loan.getDeadline());
			ps.setInt(5, loan.getUsesofloan());
			ps.setInt(6, loan.getLendtype());
			ps.setInt(7, loan.getHaspawn());
			ps.setInt(8, loan.getPawn());
			ps.setString(9, loan.getIntroduce());
			ps.setInt(10, loan.getLocation());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(11, regdate);
			
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
	public List<QdLoan> getLoanList(int size, int page, String keyword) {
		List<QdLoan> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_loan where title like ? || introduce like ? limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdLoan loan = new QdLoan();
				loan.setAmount(rs.getFloat("amount"));
				loan.setDeadline(rs.getTimestamp("deadline"));
				loan.setHaspawn(rs.getInt("haspawn"));
				loan.setIntroduce(rs.getString("introduce"));
				loan.setLendtype(rs.getInt("lendtype"));
				loan.setLid(rs.getInt("lid"));
				loan.setLocation(rs.getInt("location"));
				loan.setPawn(rs.getInt("pawn"));
				loan.setPostdate(rs.getTimestamp("postdate"));
				loan.setRNum(rs.getInt("rNum"));
				loan.setTitle(rs.getString("title"));
				loan.setUid(rs.getInt("uid"));
				loan.setUsesofloan(rs.getInt("usesofloan"));
				loan.setVerify(rs.getInt("verify"));

				list.add(loan);
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
	public QdLoan getLoan(int lid) {
		QdLoan loan = null;
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_loan where qd_loan=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, lid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				loan = new QdLoan();
				loan.setAmount(rs.getFloat("amount"));
				loan.setDeadline(rs.getTimestamp("deadline"));
				loan.setHaspawn(rs.getInt("haspawn"));
				loan.setIntroduce(rs.getString("introduce"));
				loan.setLendtype(rs.getInt("lendtype"));
				loan.setLid(rs.getInt("lid"));
				loan.setLocation(rs.getInt("location"));
				loan.setPawn(rs.getInt("pawn"));
				loan.setPostdate(rs.getTimestamp("postdate"));
				loan.setRNum(rs.getInt("rNum"));
				loan.setTitle(rs.getString("title"));
				loan.setUid(rs.getInt("uid"));
				loan.setUsesofloan(rs.getInt("usesofloan"));
				loan.setVerify(rs.getInt("verify"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return loan;
	}

	@Override
	public List<QdLoan> getLoanByUser(String username, int size, int page) {
		List<QdLoan> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_loan inner join QingDyDB.qd_member on QingDyDB.qd_loan.uid=QingDyDB.qd_member.uid where username=? limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, (page - 1) * size);
			ps.setInt(3, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				QdLoan loan = new QdLoan();
				loan.setAmount(rs.getFloat("amount"));
				loan.setDeadline(rs.getTimestamp("deadline"));
				loan.setHaspawn(rs.getInt("haspawn"));
				loan.setIntroduce(rs.getString("introduce"));
				loan.setLendtype(rs.getInt("lendtype"));
				loan.setLid(rs.getInt("lid"));
				loan.setLocation(rs.getInt("location"));
				loan.setPawn(rs.getInt("pawn"));
				loan.setPostdate(rs.getTimestamp("postdate"));
				loan.setRNum(rs.getInt("rNum"));
				loan.setTitle(rs.getString("title"));
				loan.setUid(rs.getInt("uid"));
				loan.setUsesofloan(rs.getInt("usesofloan"));
				loan.setVerify(rs.getInt("verify"));

				list.add(loan);
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
	public int updateLoan(QdLoan loan) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_loan set title=?,amount=?,deadline=?,usesofloan=?,lendtype=?,haspawn=?,pawn=?,introduce=?,location=? where lid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, loan.getTitle());
			ps.setFloat(2, loan.getAmount());
			ps.setTimestamp(3, loan.getDeadline());
			ps.setInt(4, loan.getUsesofloan());
			ps.setInt(5, loan.getLendtype());
			ps.setInt(6, loan.getHaspawn());
			ps.setInt(7, loan.getPawn());
			ps.setString(8, loan.getIntroduce());
			ps.setInt(9, loan.getLocation());
			
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
	public int verifyLoan(int lid, int verify) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_loan set verify=? where lid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, verify);
			ps.setInt(2, lid);
			
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
	public int removeLoan(int lid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_loan where lid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, lid);
			
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
