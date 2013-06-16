package com.qingdy.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qingdy.common.cJDBCUtilsSingleton;

import com.qingdy.dao.AreaDao;

public class AreaDaoImpl implements AreaDao {

	public String getProvince() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet provinceRs = null;
		String area = "";
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select name from qingdydb.qd_areas where parentid=0 group by areaid";
			ps = conn.prepareStatement(sql);
			provinceRs = ps.executeQuery();
			area += "<?xml version=\"1.0\" encoding=\"utf-8\" ?><area>";
			while (provinceRs.next()) {
				area += ("<province text=\"" + provinceRs.getString(1) + "\">");
				area += "</province>";
			}
			area += "</area>";
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(provinceRs, ps, conn);
		}
		return area;
	}

	public String getCity(String province) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet cityRs = null;
		String area = "";
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select name from qingdydb.qd_areas where parentid=(select areaid from qingdydb.qd_areas where name=?) group by vieworder";
			ps = conn.prepareStatement(sql);
			ps.setString(1, province);
			cityRs = ps.executeQuery();

			area += "<?xml version=\"1.0\" encoding=\"utf-8\" ?><area>";
			while (cityRs.next()) {	
				area += ("<city text=\"" + cityRs.getString(1) + "\">");
				area += "</city>";
			}
			area += "</area>";
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(cityRs, ps, conn);
		}
		return area;
	}

	public String getCounty(String province, String city) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet countyRs = null;
		String area = "";
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select name, areaid from qingdydb.qd_areas where parentid=(select areaid from qingdydb.qd_areas where parentid=(select areaid from qingdydb.qd_areas where name=?) and areaid in (select areaid from qingdydb.qd_areas where name=?)) group by vieworder;";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, province);
			ps.setString(2, city);
			countyRs = ps.executeQuery();
			area += "<?xml version=\"1.0\" encoding=\"utf-8\" ?><area>";
			while (countyRs.next()) {
				area += ("<county text=\"" + countyRs.getString(1) + "\">" + countyRs.getString(2) + "</county>");
			}

			area += "</area>";
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(countyRs, ps, conn);
		}
		return area;
	}

	@Override
	public List<String> getCounty(String username) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet countyRs = null;
		String sql;
		List<String> area = new ArrayList<String>();
		
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			
			sql = "select name, areaid from qingdydb.qd_areas where parentid=(select parentid from qingdydb.qd_areas where areaid=(select apartment from qingdydb.qd_members where username=?));";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			countyRs = ps.executeQuery();
			
			while (countyRs.next()) {
				area.add(countyRs.getString(1)/* + ";" + countyRs.getString(2)*/);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(countyRs, ps, conn);
		}
		return area;
	}

	@Override
	public String getApartment(int areaid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String apartment = "";
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select joinname from qingdydb.qd_areas where areaid=?;";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, areaid);
			rs = ps.executeQuery();
			if (rs.next()) {
				apartment += rs.getString("joinname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		
		return apartment.substring(0, apartment.lastIndexOf(","));
	}

	@Override
	public String getAllCities(String province) throws UnsupportedEncodingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet cityRs = null;
		ResultSet allCitiesRs = null;
		String area = "";
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();
			
			sql = "select name from qingdydb.qd_areas where parentid=(select areaid from qingdydb.qd_areas where name=?) group by vieworder";
			ps = conn.prepareStatement(sql);
			ps.setString(1, province);
			cityRs = ps.executeQuery();
			
			area += "<?xml version=\"1.0\" encoding=\"utf-8\" ?><area>";
			// ֱϽ��
			if (province.contains("��")) {
				while (cityRs.next()) {
					sql = "select name, areaid from qingdydb.qd_areas where parentid=(select areaid from qingdydb.qd_areas where parentid=(select areaid from qingdydb.qd_areas where name=?) and areaid in (select areaid from qingdydb.qd_areas where name=?)) group by vieworder;";
					ps = conn.prepareStatement(sql);
					ps.setString(1, province);
					ps.setString(2, cityRs.getString("name"));
					allCitiesRs = ps.executeQuery();
					while (allCitiesRs.next()) {	
						area += ("<city text=\"" + allCitiesRs.getString(1) + "\">");
						area += "</city>";
					}
				}
			}
			else {
				while (cityRs.next()) {	
					area += ("<city text=\"" + cityRs.getString(1) + "\">");
					area += "</city>";
				}
			}

			area += "</area>";
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(cityRs, ps, conn);
			cJDBCUtilsSingleton.getInstance().free(allCitiesRs, ps, conn);
		}
		return area;
	}

}
