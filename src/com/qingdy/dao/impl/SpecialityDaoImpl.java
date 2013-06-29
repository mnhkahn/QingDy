package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.SpecialityDao;
import com.qingdy.domain.Areas;
import com.qingdy.domain.Speciality;

public class SpecialityDaoImpl extends CDaoImpl implements SpecialityDao {

	@Override
	public List<Speciality> getSpecialities() {
		List<Speciality> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.speciality";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Speciality speciality = new Speciality();
				speciality.setSpeid(rs.getInt("speid"));
				speciality.setSpeciality(rs.getString("speciality"));
				speciality.setParentid(rs.getString("parentid"));
				list.add(speciality);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return list;
	}

}
