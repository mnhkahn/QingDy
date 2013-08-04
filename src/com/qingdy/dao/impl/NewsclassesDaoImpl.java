package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.NewsclassesDao;
import com.qingdy.model.Areas;
import com.qingdy.model.Newsclasses;

public class NewsclassesDaoImpl extends CDaoImpl implements NewsclassesDao {

	@Override
	public List<Newsclasses> getNewsclasses() {
		List<Newsclasses> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.newsclasses";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Newsclasses newsclasses = new Newsclasses();
				newsclasses.setNcid(rs.getInt("ncid"));
				newsclasses.setNewsclasses(rs.getString("newsclasses"));
				list.add(newsclasses);
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
