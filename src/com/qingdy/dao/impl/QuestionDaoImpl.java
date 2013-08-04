package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.QuestionDao;
import com.qingdy.model.QdAnswer;
import com.qingdy.model.QdQuestion;

public class QuestionDaoImpl extends CDaoImpl implements QuestionDao {

	@Override
	public int addQuestion(QdQuestion question) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_question(uid,title,question,classes,postdate) values(?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, question.getUid());
			ps.setString(2, question.getTitle());
			ps.setString(3, question.getQuestion());
			ps.setInt(4, question.getClasses());
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
	public List<QdQuestion> getQdQuestionList(int size, int page, String keyword) {
		List<QdQuestion> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_question where title like ? || question like ? limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdQuestion question = new QdQuestion();
				question.setQid(rs.getInt("qid"));
				question.setUid(rs.getInt("uid"));
				question.setTitle(rs.getString("title"));
				question.setQuestion(rs.getString("question"));
				question.setClasses(rs.getInt("classes"));
				question.setPostdate(rs.getTimestamp("postdate"));
				question.setIscompleted(rs.getInt("iscompleted"));

				list.add(question);
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
	public QdQuestion getQuestion(int qid) {
		QdQuestion question = null;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_question where qid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				question = new QdQuestion();
				question.setQid(rs.getInt("qid"));
				question.setUid(rs.getInt("uid"));
				question.setTitle(rs.getString("title"));
				question.setQuestion(rs.getString("question"));
				question.setClasses(rs.getInt("classes"));
				question.setPostdate(rs.getTimestamp("postdate"));
				question.setIscompleted(rs.getInt("iscompleted"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return question;
	}

	@Override
	public List<QdQuestion> getQuestionByUser(String username) {
		List<QdQuestion> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_question left outer join QingDyDB.qd_member on QingDyDB.qd_question.uid=QingDyDB.qd_member.uid where username=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdQuestion question = new QdQuestion();
				question.setQid(rs.getInt("qid"));
				question.setUid(rs.getInt("uid"));
				question.setTitle(rs.getString("title"));
				question.setQuestion(rs.getString("question"));
				question.setClasses(rs.getInt("classes"));
				question.setPostdate(rs.getTimestamp("postdate"));
				question.setIscompleted(rs.getInt("iscompleted"));

				list.add(question);
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
	public int updateQuestion(QdQuestion question) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_question set title=?,question=?,classes=? where qid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, question.getTitle());
			ps.setString(2, question.getQuestion());
			ps.setInt(3, question.getClasses());
			ps.setInt(4, question.getQid());
			
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
	public int verifyQuestion(int qid, int verify) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_question set isCompleted=? where qid=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, verify);
			ps.setInt(2, qid);
			
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
	public int removeQuestion(int qid) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_question where qid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qid);
			
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
