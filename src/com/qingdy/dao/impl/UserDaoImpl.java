package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.UserDao;
import com.qingdy.domain.QdAnswer;
import com.qingdy.domain.QdMember;

public class UserDaoImpl extends CDaoImpl implements UserDao {

	@Override
	public int addUser(QdMember user) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_member(username,password,groupid,postdate) values(?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getGroupid());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(4, regdate);
			
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
	public QdMember getUser(String username, String password) {
		System.out.println(username + "|" + password);
		QdMember user = null;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_member where username=? && password=MD5(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new QdMember();
				user.setUid(rs.getInt("uid"));
				user.setUsername(username);
				user.setPassword(password);
				user.setGroupid(rs.getInt("groupid"));
				user.setLastname(rs.getString("lastname"));
				user.setFirstname(rs.getString("firstname"));
				user.setGender(rs.getInt("gender"));
				user.setIcon(rs.getString("icon"));
				user.setPhone(rs.getString("phone"));
				user.setQq(rs.getString("qq"));
				user.setMsn(rs.getString("msn"));
				user.setSite(rs.getString("site"));
				user.setEmail(rs.getString("email"));
				user.setSignature(rs.getString("signature"));
				user.setIntroduce(rs.getString("introduce"));
				user.setLocation(rs.getInt("location"));
				user.setBirthday(rs.getTimestamp("birthday"));
				user.setRegdate(rs.getTimestamp("regdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return user;
	}

	@Override
	public int updateUser(QdMember user) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_member set lastname=?,firstname=?,phone=?,qq=?,msn=?,site=?,email=?,signature=?,introduce=?,location=?,birtyday=?,regdate=? where uid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getLastname());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getQq());
			ps.setString(5, user.getMsn());
			ps.setString(6, user.getSite());
			ps.setString(7, user.getEmail());
			ps.setString(8, user.getSignature());
			ps.setString(9, user.getIntroduce());
			ps.setInt(10, user.getLocation());
			ps.setTimestamp(11, user.getBirthday());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(12, regdate);
			ps.setInt(13, user.getUid());
			
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
	public int getUid(String username) {
		int isExists = 0;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_member where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				isExists = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return isExists;
	}

}
