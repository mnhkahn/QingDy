package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.QuestionclassesDao;
import com.qingdy.model.Areas;
import com.qingdy.model.Questionclasses;

public class QuestionclassesDaoImpl extends CDaoImpl implements QuestionclassesDao {

	@Override
	public List<Questionclasses> getQuestionclasses() {
		List<Questionclasses> list = new LinkedList<>();
		String sql;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.questionclasses";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Questionclasses questionclasses = new Questionclasses();
				questionclasses.setClassid(rs.getInt("classid"));
				questionclasses.setName(rs.getString("questionclassesname"));
				questionclasses.setParentid(rs.getInt("parentid"));
				list.add(questionclasses);
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
