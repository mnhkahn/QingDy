package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.AnswerDao;
import com.qingdy.domain.QdAnswer;
import com.qingdy.domain.QdNews;

public class AnswerDaoImpl extends CDaoImpl implements AnswerDao {

	@Override
	public int addAnswer(QdAnswer answer) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_answer(uid,answer,postdate,qid) values(?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, answer.getUid());
			ps.setString(2, answer.getAnswer());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(3, regdate);
			ps.setInt(4, answer.getQid());
			
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
	public List<QdAnswer> getAnswerList(int size, int page, String keyword) {
		List<QdAnswer> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_answer where title like ? || answer like ? limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdAnswer answer = new QdAnswer();
				answer.setAid(rs.getInt("aid"));
				answer.setUid(rs.getInt("uid"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPostdate(rs.getTimestamp("postdate"));
				answer.setIsAnswer(rs.getInt("isAnswer"));
				answer.setQid(rs.getInt("qid"));

				list.add(answer);
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
	public QdAnswer getAnswer(int aid) {
		QdAnswer answer = null;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_answer where aid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				answer = new QdAnswer();
				answer.setAid(rs.getInt("aid"));
				answer.setUid(rs.getInt("uid"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPostdate(rs.getTimestamp("postdate"));
				answer.setIsAnswer(rs.getInt("isAnswer"));
				answer.setQid(rs.getInt("qid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return answer;
	}

	@Override
	public List<QdAnswer> getAnswerByUser(String username) {
		List<QdAnswer> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_answer left outer join QingDyDB.qd_member on QingDyDB.qd_answer.uid=QingDyDB.qd_member.uid where username=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdAnswer answer = new QdAnswer();
				answer.setAid(rs.getInt("aid"));
				answer.setUid(rs.getInt("uid"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPostdate(rs.getTimestamp("postdate"));
				answer.setIsAnswer(rs.getInt("isAnswer"));
				answer.setQid(rs.getInt("qid"));

				list.add(answer);
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
	public int updateAnswer(QdAnswer answer) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_answer set answer=? where aid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, answer.getAnswer());
			ps.setInt(2, answer.getAid());
			
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
	public int verifyAnswer(int aid, int verify) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_answer set isAnswer=? where aid=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, verify);
			ps.setInt(2, aid);
			
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
	public int removeAnswer(int aid) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_answer where aid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			
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
	public List<QdAnswer> getAnswerByQuestion(int qid) {
		List<QdAnswer> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_answer left outer join QingDyDB.qd_question on QingDyDB.qd_answer.qid=QingDyDB.qd_question.qid where QingDyDB.qd_question.qid=?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, qid);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdAnswer answer = new QdAnswer();
				answer.setAid(rs.getInt("aid"));
				answer.setUid(rs.getInt("uid"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPostdate(rs.getTimestamp("postdate"));
				answer.setIsAnswer(rs.getInt("isAnswer"));
				answer.setQid(rs.getInt("qid"));

				list.add(answer);
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
