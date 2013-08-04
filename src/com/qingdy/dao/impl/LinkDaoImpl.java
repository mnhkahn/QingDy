package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.LinkDao;
import com.qingdy.model.QdLink;

public class LinkDaoImpl extends CDaoImpl implements LinkDao {

	@Override
	public int addLink(QdLink link) {
		System.out.println("$$$" + link.getIntroduce());
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_link(introduce,name,site,uid) values(?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, link.getIntroduce());
			ps.setString(2, link.getName());
			ps.setString(3, link.getSite());
			ps.setInt(4, link.getUid());
			
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
	public List<QdLink> getLinkList(int size, int page) {
		List<QdLink> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_link limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, (page - 1) * size);
			ps.setInt(2, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdLink link = new QdLink();
				link.setIntroduce(rs.getString("introduce"));
				link.setLid(rs.getInt("lid"));
				link.setName(rs.getString("name"));
				link.setSite(rs.getString("site"));
				link.setSort(rs.getInt("sort"));
				link.setUid(rs.getInt("uid"));
			
				list.add(link);
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
	public int updateLink(QdLink link) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_link set introduce=?,name=?,site=?,uid=?,sort=? where lid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, link.getIntroduce());
			ps.setString(2, link.getName());
			ps.setString(3, link.getSite());
			ps.setInt(4, link.getUid());
			ps.setInt(5, link.getSort());
			ps.setInt(6, link.getLid());
			
			
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
	public int removeLink(int lid) {
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_link where lid=?";
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
