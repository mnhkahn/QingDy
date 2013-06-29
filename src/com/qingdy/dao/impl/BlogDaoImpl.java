package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.BlogDao;
import com.qingdy.domain.QdBlog;

public class BlogDaoImpl extends CDaoImpl implements BlogDao {

	@Override
	public int addBlog(QdBlog blog) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_blog(blog,classes,title,frontcover,uid) values(?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, blog.getBlog());
			ps.setInt(2, blog.getClasses());
			ps.setString(3, blog.getTitle());
			ps.setString(4, blog.getFrontcover());
			ps.setInt(5, blog.getUid());
			
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
	public List<QdBlog> getBlogList(int size, int page, String keyword) {
		List<QdBlog> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_blog where title like ? || blog like ? limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdBlog blog = new QdBlog();
				blog.setBid(rs.getInt("bid"));
				blog.setBlog(rs.getString("blog"));
				blog.setClasses(rs.getInt("classes"));
				blog.setTitle(rs.getString("title"));
				blog.setFrontcover(rs.getString("frontcover"));
				blog.setUid(rs.getInt("uid"));

				list.add(blog);
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
	public QdBlog getBlog(int bid) {
		QdBlog blog = null;
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_blog where bid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				blog = new QdBlog();
				blog.setBid(rs.getInt("bid"));
				blog.setBlog(rs.getString("blog"));
				blog.setClasses(rs.getInt("classes"));
				blog.setTitle(rs.getString("title"));
				blog.setFrontcover(rs.getString("frontcover"));
				blog.setUid(rs.getInt("uid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return blog;
	}

	@Override
	public List<QdBlog> getBlogByUser(String username, int size, int page) {
		List<QdBlog> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_blog left outer join QingDy.qd_member QingDyDB.qd_blog.uid=QingDy.qd_member.uid where username=? limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, (page - 1) * size);
			ps.setInt(3, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				QdBlog blog = new QdBlog();
				blog.setBid(rs.getInt("bid"));
				blog.setBlog(rs.getString("blog"));
				blog.setClasses(rs.getInt("classes"));
				blog.setTitle(rs.getString("title"));
				blog.setFrontcover(rs.getString("frontcover"));
				blog.setUid(rs.getInt("uid"));

				list.add(blog);
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
	public int updateBlog(QdBlog blog) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_blog set blog=?, classes=?, title=?, frontcover=? where bid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, blog.getBlog());
			ps.setInt(2, blog.getClasses());
			ps.setString(3, blog.getTitle());
			ps.setString(4, blog.getFrontcover());
			ps.setInt(5, blog.getBid());
			
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
	public int removeBlog(int bid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_blog where bid=?";
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
