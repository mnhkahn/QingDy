package com.qingdy.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.RowSet;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.SQLParameters;
import com.qingdy.dao.TransactionDao;
import com.qingdy.domain.Grid;
import com.qingdy.domain.QdTransaction;
import com.qingdy.domain.Row;

public class TransactionDaoImpl extends CDaoImpl implements TransactionDao {

	@Override
	public int addTransaction(QdTransaction transaction) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_transaction(lenderid,loanerid,title,comments,frontcover,postdate) values(?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, transaction.getLenderid());
			ps.setInt(2, transaction.getLoanerid());
			ps.setString(3, transaction.getTitle());
			ps.setString(4, transaction.getComments());
			ps.setString(5, transaction.getFrontcover());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(6, regdate);
			
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
	public Grid geTransactionList(SQLParameters parameters) {
		this.parameter = parameters;
		Grid grid = new Grid();

		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			sql = "select * from QingDyDB.qd_transaction left outer join QingDyDB.qd_member on QingDyDB.qd_transaction.loanerid=QingDyDB.qd_member.uid where true ";
			setBaseSql(sql);
			sql = generateSql();
			
			ps = conn.prepareStatement(sql);
			fillPreparedStatement(ps);
			
			rs = ps.executeQuery();
			
			sql = "select * from QingDyDB.qd_transaction left outer join QingDyDB.qd_member on QingDyDB.qd_transaction.lenderid=QingDyDB.qd_member.uid where true ";	
			ps = conn.prepareStatement(sql);	
			ResultSet rs1 = ps.executeQuery();
			List rows = new LinkedList<>();
			while (rs.next() && rs1.next()) {
				Row row = new Row();
				row.setId(rs.getInt("tid"));
				List transaction = new LinkedList<>();
				transaction.add(rs.getInt("tid"));
				transaction.add(rs.getString("title"));
				transaction.add(rs.getString("lastname") + rs.getString("firstname"));
				transaction.add(rs1.getString("lastname") + rs1.getString("firstname"));
				transaction.add(rs.getString("comments"));
				transaction.add(rs.getString("frontcover"));
				transaction.add(rs.getTimestamp("postdate").toLocaleString());
				transaction.add(rs.getInt("verify"));

				row.setCell(transaction);
				rows.add(row);
			}
			
			grid.setPage(parameters.getPage());
			
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			sql = "SELECT count(*) as count FROM QingDyDB.qd_blog;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int records = rs.getInt("count");
				grid.setTotal((int)Math.ceil((double)records / (double)parameters.getSize()));
				grid.setRecords(records);
			}
			
			grid.setRows(rows);

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return grid;
	}

	@Override
	public QdTransaction getTransaction(int tid) {
		QdTransaction transaction = null;
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_transaction where tid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				transaction = new QdTransaction();
				transaction.setLenderid(rs.getInt("lenderid"));
				transaction.setLoanerid(rs.getInt("loanerid"));
				transaction.setSort(rs.getInt("sort"));
				transaction.setTid(rs.getInt("tid"));
				transaction.setTitle(rs.getString("title"));
				transaction.setComments(rs.getString("comments"));
				transaction.setFrontcover(rs.getString("frontcover"));
				transaction.setPostdate(rs.getTimestamp("postdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return transaction;
	}

	@Override
	public List<QdTransaction> getTransactionByUser(String username, int size, int page) {
		List<QdTransaction> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_transaction inner join QingDyDB.qd_member on QingDyDB.qd_transaction.lenderid=QingDyDB.qd_member.uid where username=? limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, (page - 1) * size);
			ps.setInt(3, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				QdTransaction transaction = new QdTransaction();
				transaction.setLenderid(rs.getInt("lenderid"));
				transaction.setLoanerid(rs.getInt("loanerid"));
				transaction.setSort(rs.getInt("sort"));
				transaction.setTid(rs.getInt("tid"));
				transaction.setTitle(rs.getString("title"));
				transaction.setComments(rs.getString("comments"));
				transaction.setFrontcover(rs.getString("frontcover"));
				transaction.setPostdate(rs.getTimestamp("postdate"));

				list.add(transaction);
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
	public int updateTransaction(QdTransaction transaction) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_transaction set lenderid=?,loanerid=?,sort=?,title=?,comments=?,frontcover=? where tid=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, transaction.getLenderid());
			ps.setInt(2, transaction.getLoanerid());
			ps.setInt(3, transaction.getSort());
			ps.setString(4, transaction.getTitle());
			ps.setString(5, transaction.getComments());
			ps.setString(6, transaction.getFrontcover());
			ps.setInt(7, transaction.getTid());
			
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
	public int verifyTransaction(int tid, int verify) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_transaction set verify=? where tid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, verify);
			ps.setInt(2, tid);
			
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
	public int removeTransaction(int lid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_transaction where tid=?";
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
