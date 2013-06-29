package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.NewsDao;
import com.qingdy.domain.QdNews;

public class NewsDaoImpl extends CDaoImpl implements NewsDao {

	@Override
	public int addNews(QdNews news) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_news(title,text,uid,classes,postdate) values(?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getText());
			ps.setInt(3, news.getUid());
			ps.setInt(4, news.getClasses());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(5, regdate);
			
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
	public List<QdNews> getNewsList(int size, int page, String keyword) {
		List<QdNews> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_news where title like ? || text like ? limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdNews news = new QdNews();
				news.setNid(rs.getInt("nid"));
				news.setTitle(rs.getString("title"));
				news.setText(rs.getString("text"));
				news.setUid(rs.getInt("uid"));
				news.setClasses(rs.getInt("classes"));
				news.setPostdate(rs.getTimestamp("postdate"));			

				list.add(news);
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
	public QdNews getNews(int nid) {
		QdNews news = null;
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_news where nid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				news = new QdNews();
				news.setNid(rs.getInt("nid"));
				news.setTitle(rs.getString("title"));
				news.setText(rs.getString("text"));
				news.setUid(rs.getInt("uid"));
				news.setClasses(rs.getInt("classes"));
				news.setPostdate(rs.getTimestamp("postdate"));			
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return news;
	}

	@Override
	public int updateNews(QdNews news) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_news set title=?,text=?,classes=? where nid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getText());
			ps.setInt(3, news.getClasses());
			ps.setInt(4, news.getNid());
			
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
	public int removeNews(int nid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_news where nid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nid);
			
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
