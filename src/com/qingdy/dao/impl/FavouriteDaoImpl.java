package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.FavouriteDao;
import com.qingdy.model.QdFavourite;
import com.qingdy.model.QdTransaction;

public class FavouriteDaoImpl extends CDaoImpl implements FavouriteDao {

	@Override
	public int addFavourite(QdFavourite favourite) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_favourite(title,site,postdate,uid) values(?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, favourite.getTitle());
			ps.setString(2, favourite.getSite());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(3, regdate);
			ps.setInt(4, favourite.getUid());
			
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
	public List<QdFavourite> getFavouriteList(int size, int page) {
		List<QdFavourite> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_favourite limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, (page - 1) * size);
			ps.setInt(2, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdFavourite favourite = new QdFavourite();
				favourite.setTitle(rs.getString("title"));
				favourite.setSite(rs.getString("site"));
				favourite.setFid(rs.getInt("fid"));
				favourite.setPostdate(rs.getTimestamp("postdate"));
				favourite.setUid(rs.getInt("uid"));
				
				list.add(favourite);
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
	public int updateFavourite(QdFavourite favourite) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_favourite set title=?,site=?,postdate=?,uid=? where uid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, favourite.getTitle());
			ps.setString(2, favourite.getSite());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(3, regdate);
			ps.setInt(4, favourite.getUid());
			ps.setInt(5, favourite.getFid());
			
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
	public int removeFavourite(int fid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_favourite where fid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);
			
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
