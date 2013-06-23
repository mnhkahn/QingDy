package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.AreaDao;
import com.qingdy.domain.Areas;

public class AreaDaoImpl extends CDaoImpl implements AreaDao {

	@Override
	public List<Areas> getProvince() {
		List<Areas> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.areas where parentid=0 group by areaid";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Areas areas = new Areas();
				areas.setAreaid(rs.getInt("areaid"));
				areas.setJoinname(rs.getString("joinname"));
				areas.setName(rs.getString("name"));
				areas.setParentid(rs.getInt("parentid"));
				areas.setVieworder(rs.getInt("vieworder"));
				list.add(areas);
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
	public List<Areas> getCity(int province) {
		List<Areas> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.areas where parentid=(select areaid from QingDyDB.areas where areaid=?) group by vieworder";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, province);
			rs = ps.executeQuery();

			while (rs.next()) {	
				Areas areas = new Areas();
				areas.setAreaid(rs.getInt("areaid"));
				areas.setJoinname(rs.getString("joinname"));
				areas.setName(rs.getString("name"));
				areas.setParentid(rs.getInt("parentid"));
				areas.setVieworder(rs.getInt("vieworder"));
				list.add(areas);
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
	public List<Areas> getCounty(int city) {
		List<Areas> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.areas where parentid=(select areaid from QingDyDB.areas where areaid=?) group by vieworder";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, city);
			rs = ps.executeQuery();

			while (rs.next()) {	
				Areas areas = new Areas();
				areas.setAreaid(rs.getInt("areaid"));
				areas.setJoinname(rs.getString("joinname"));
				areas.setName(rs.getString("name"));
				areas.setParentid(rs.getInt("parentid"));
				areas.setVieworder(rs.getInt("vieworder"));
				list.add(areas);
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
	public List<Areas> getFullLocation(int areaId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
