package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.MallDao;
import com.qingdy.dao.SQLParameters;
import com.qingdy.domain.Grid;
import com.qingdy.domain.Mall;
import com.qingdy.domain.QdAnswer;
import com.qingdy.domain.QdMall;
import com.qingdy.domain.Row;

public class MallDaoImpl extends CDaoImpl implements MallDao {

	@Override
	public int addMall(QdMall mall) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_mall(uid,answer,postdate,qid) values(?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mall.getUid());
			ps.setString(2, mall.getIntroduce());
			ps.setString(3, mall.getAnnouncement());
			ps.setInt(4, mall.getLendedyears());
			ps.setString(5, mall.getCtype());
			ps.setString(6, mall.getCname());
			ps.setString(7, mall.getCphonenumber());
			ps.setString(8, mall.getCsite());
			ps.setString(9, mall.getCemail());
			ps.setString(10, mall.getCaddress());
			ps.setString(11, mall.getCfax());
			ps.setString(12, mall.getCpostcode());
			ps.setInt(13, mall.getClientlocation());
			ps.setInt(14, mall.getUsesofloan());
			ps.setInt(15, mall.getSpeciality());
			ps.setInt(16, mall.getClients());
			ps.setInt(17, mall.getLendtype());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(18, regdate);
			
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
	public List<QdMall> getVerifiedMallList(int size, int page, String keyword) {
		List<QdMall> list = new ArrayList<QdMall>();

		try {

			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM QingDyDB.qd_mall left outer join QingDyDB.qd_member on QingDyDB.qd_mall.uid=QingDyDB.qd_member.uid left outer join QingDyDB.areas on QingDyDB.qd_mall.clientlocation=QingDyDB.areas.areaid left outer join QingDyDB.usersofloan on QingDyDB.qd_mall.usesofloanid=QingDyDB.usersofloan.uolid left outer join QingDyDB.speciality on QingDyDB.qd_mall.specialityid=QingDyDB.speciality.speid left outer join QingDyDB.clients on QingDyDB.qd_mall.lendtypeid=QingDyDB.clients.ctid left outer join QingDyDB.lendtype on QingDyDB.qd_mall.lendtypeid=QingDyDB.lendtype.ltid left outer join QingDyDB.lendedyears on QingDyDB.qd_mall.lendedyearsid=QingDyDB.lendedyears.lid";
			
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdMall mall = new QdMall();
				mall.setMid(rs.getInt("mid"));
				mall.setUid(rs.getInt("uid"));
				mall.setAnnouncement(rs.getString("announcement"));
				mall.setIntroduce(rs.getString("introduce"));
				mall.setLendedyears(rs.getInt("lendedyears"));
				mall.setCtype(rs.getString("ctype"));
				mall.setCname(rs.getString("cname"));
				mall.setCphonenumber(rs.getString("cphonenumber"));
				mall.setCpostcode(rs.getString("cpostcode"));
				mall.setCsite(rs.getString("csite"));
				mall.setCemail(rs.getString("cemail"));
				mall.setCaddress(rs.getString("caddress"));
				mall.setCfax(rs.getString("cfax"));
				mall.setClientlocation(rs.getInt("clientlocation"));
				mall.setUsesofloan(rs.getInt("usesofloan"));
				mall.setSpeciality(rs.getInt("speciality"));
				mall.setClients(rs.getInt("clients"));
				mall.setLendtype(rs.getInt("lendtype"));
				mall.setRNum(rs.getInt("r_num"));
				mall.setVerify(rs.getInt("verify"));
				
				list.add(mall);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e);
			
		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		
		return list;
	}
	
	public Grid getAllMallList(SQLParameters parameters) {
		this.parameter = parameters;
		Grid grid = new Grid();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			sql = "SELECT * FROM QingDyDB.qd_mall left outer join QingDyDB.qd_member on QingDyDB.qd_mall.uid=QingDyDB.qd_member.uid left outer join QingDyDB.areas on QingDyDB.qd_mall.clientlocation=QingDyDB.areas.areaid left outer join QingDyDB.usersofloan on QingDyDB.qd_mall.usesofloanid=QingDyDB.usersofloan.uolid left outer join QingDyDB.speciality on QingDyDB.qd_mall.specialityid=QingDyDB.speciality.speid left outer join QingDyDB.clients on QingDyDB.qd_mall.lendtypeid=QingDyDB.clients.ctid left outer join QingDyDB.lendtype on QingDyDB.qd_mall.lendtypeid=QingDyDB.lendtype.ltid left outer join QingDyDB.lendedyears on QingDyDB.qd_mall.lendedyearsid=QingDyDB.lendedyears.lid where true ";
			setBaseSql(sql);
			sql = generateSql();

			ps = conn.prepareStatement(sql);
			
			fillPreparedStatement(ps);

/*			ps.setString(1, condition);
			ps.setString(2, sort);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);*/
			rs = ps.executeQuery();

			List rows = new LinkedList<>();
			while (rs.next()) {
				Row row = new Row();
				row.setId(rs.getInt("mid"));

				List mall = new LinkedList<>();
				mall.add(rs.getInt("mid"));
				mall.add(rs.getString("lastname") + rs.getString("firstname"));
				mall.add(rs.getString("announcement"));
				mall.add(rs.getString("introduce"));
				mall.add(rs.getString("lendedyears"));
				mall.add(rs.getString("ctype"));
				mall.add(rs.getString("cname"));
				mall.add(rs.getString("cphonenumber"));
				mall.add(rs.getString("cpostcode"));
				mall.add(rs.getString("csite"));
				mall.add(rs.getString("cemail"));
				mall.add(rs.getString("caddress"));
				mall.add(rs.getString("cfax"));
				mall.add(rs.getString("joinname"));
				mall.add(rs.getString("usesofloan"));
				mall.add(rs.getString("speciality"));
				mall.add(rs.getString("lendtype"));
				mall.add(rs.getTimestamp("regdate").toLocaleString());
				mall.add(rs.getInt("verify"));
				
				row.setCell(mall);
				rows.add(row);
			}
			
			grid.setPage(parameters.getPage());
			
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			sql = "SELECT count(*) as count FROM QingDyDB.qd_mall;";
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
//			throw new SQLException(e.getMessage(), e);
			
		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return grid;
	}

	@Override
	public QdMall getMall(int mid) {
		QdMall mall = null;

		try {

			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM QingDyDB.qd_mall where mid=?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, mid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				mall = new QdMall();
				mall.setMid(rs.getInt("mid"));
				mall.setUid(rs.getInt("uid"));
				mall.setAnnouncement(rs.getString("announcement"));
				mall.setIntroduce(rs.getString("introduce"));
				mall.setLendedyears(rs.getInt("lendedyears"));
				mall.setCtype(rs.getString("ctype"));
				mall.setCname(rs.getString("cname"));
				mall.setCphonenumber(rs.getString("cphonenumber"));
				mall.setCpostcode(rs.getString("cpostcode"));
				mall.setCsite(rs.getString("csite"));
				mall.setCemail(rs.getString("cemail"));
				mall.setCaddress(rs.getString("caddress"));
				mall.setCfax(rs.getString("cfax"));
				mall.setClientlocation(rs.getInt("clientlocation"));
				mall.setUsesofloan(rs.getInt("usesofloan"));
				mall.setSpeciality(rs.getInt("speciality"));
				mall.setClients(rs.getInt("clients"));
				mall.setLendtype(rs.getInt("lendtype"));
				mall.setRNum(rs.getInt("r_num"));
				mall.setVerify(rs.getInt("verify"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e);
			
		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		
		return mall;
	}

	@Override
	public QdMall getMallByUser(String username) {
		QdMall mall = null;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_mall left outer join QingDyDB.qd_member on QingDyDB.qd_mall.uid=QingDyDB.qd_member.uid where username=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				mall = new QdMall();
				mall.setMid(rs.getInt("mid"));
				mall.setUid(rs.getInt("uid"));
				mall.setAnnouncement(rs.getString("announcement"));
				mall.setIntroduce(rs.getString("introduce"));
				mall.setLendedyears(rs.getInt("lendedyears"));
				mall.setCtype(rs.getString("ctype"));
				mall.setCname(rs.getString("cname"));
				mall.setCphonenumber(rs.getString("cphonenumber"));
				mall.setCpostcode(rs.getString("cpostcode"));
				mall.setCsite(rs.getString("csite"));
				mall.setCemail(rs.getString("cemail"));
				mall.setCaddress(rs.getString("caddress"));
				mall.setCfax(rs.getString("cfax"));
				mall.setClientlocation(rs.getInt("clientlocation"));
				mall.setUsesofloan(rs.getInt("usesofloan"));
				mall.setSpeciality(rs.getInt("speciality"));
				mall.setClients(rs.getInt("clients"));
				mall.setLendtype(rs.getInt("lendtype"));
				mall.setRNum(rs.getInt("r_num"));
				mall.setVerify(rs.getInt("verify"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return mall;
	}

	@Override
	public int updateMall(QdMall mall) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_mall set introduce=?,announcement=?,lendedyears=?,ctype=?,cname=?,cphonenumber=?,csite=?,cemail=?,caddress=?,cfax=?,cpostcode=?,clientlocation=?,usesofloan=?,speciality=?,clients=?,lendtype=? where mid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mall.getIntroduce());
			ps.setString(2, mall.getAnnouncement());
			ps.setInt(3, mall.getLendedyears());
			ps.setString(4, mall.getCtype());
			ps.setString(5, mall.getCname());
			ps.setString(6, mall.getCphonenumber());
			ps.setString(7, mall.getCsite());
			ps.setString(8, mall.getCemail());
			ps.setString(9, mall.getCaddress());
			ps.setString(10, mall.getCfax());
			ps.setString(11, mall.getCpostcode());
			ps.setInt(12, mall.getClientlocation());
			ps.setInt(13, mall.getUsesofloan());
			ps.setInt(14, mall.getSpeciality());
			ps.setInt(15, mall.getClients());
			ps.setInt(16, mall.getLendtype());
			ps.setInt(17, mall.getMid());
			
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
	public int verifyMall(int mid, int verify) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_mall set verify=? where mid=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, verify);
			ps.setInt(2, mid);
			
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
	public int removeMall(int mid) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_mall where mid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mid);
			
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
