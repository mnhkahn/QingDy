package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.MessageDao;
import com.qingdy.domain.QdMessage;

public class MessageDaoImpl extends CDaoImpl implements MessageDao {

	@Override
	public List<QdMessage> getMessageList(int size, int page, int touid) {
		List<QdMessage> list = new ArrayList<QdMessage>();

		try {

			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM QingDyDB.qd_message where touid=? order by isreaded desc limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, touid);
			ps.setInt(2, (page - 1) * size);
			ps.setInt(3, size);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdMessage message = new QdMessage();
				message.setMid(rs.getInt("mid"));
				message.setMessage(rs.getString("message"));
				message.setFromuid(rs.getInt("fromuid"));
				message.setTouid(rs.getInt("touid"));
				message.setTitle(rs.getString("title"));
				message.setPostdate(rs.getTimestamp("postdate"));
				message.setIsreaded(rs.getInt("isreaded"));
				
				list.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e);
			
		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		
		return list;
	}

	@Override
	public int addMessage(QdMessage message) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			String sql = "INSERT INTO `QingDyDB`.`qd_message`(`fromuid`,`touid`,`title`,`message`,`postdate`,`isreaded`)VALUES(?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, message.getFromuid());
			ps.setInt(2, message.getTouid());
			ps.setString(3, message.getTitle());
			ps.setString(4, message.getMessage());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(5, regdate);
			ps.setInt(6, 0);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e);
			return 0;
			
		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return 1;
	}

	@Override
	public int getUnreadCount(int touid) {
		try {

			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			String sql = "SELECT count(*) as count FROM QingDyDB.qd_message where touid=? && isreaded=0";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, touid);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e);
			
		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return 0;
	}

}
