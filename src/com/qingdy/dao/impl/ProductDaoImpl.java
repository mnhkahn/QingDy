package com.qingdy.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.qingdy.common.CDaoImpl;
import com.qingdy.common.cJDBCUtilsSingleton;
import com.qingdy.dao.ProductDao;
import com.qingdy.domain.QdAnswer;
import com.qingdy.domain.QdProduct;

public class ProductDaoImpl extends CDaoImpl implements ProductDao {

	@Override
	public int addProduct(QdProduct product) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "insert into QingDyDB.qd_product(uid,mid,pname,max,min,ratetype,rate,deadline,clientlocation,client,repaymentmethod,ptype,usesofloan,introduce,processes,application,faq,postdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getUid());
			ps.setInt(2, product.getMid());
			ps.setString(3, product.getPname());
			ps.setInt(4, product.getMax());
			ps.setInt(5, product.getMin());
			ps.setInt(6, product.getRatetype());
			ps.setFloat(7, product.getRate());
			ps.setTimestamp(8, product.getDeadline());
			ps.setInt(9, product.getClientlocation());
			ps.setInt(10, product.getClient());
			ps.setInt(11, product.getRepaymentmethod());
			ps.setInt(12, product.getPtype());
			ps.setInt(13, product.getUsesofloan());
			ps.setString(14, product.getIntroduce());
			ps.setString(15, product.getProcesses());
			ps.setString(16, product.getApplication());
			ps.setString(17, product.getFaq());
			Date date = new Date();
			Timestamp regdate = new Timestamp(date.getTime());
			ps.setTimestamp(18, regdate);
			
			
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
	public List<QdProduct> getProductList(int size, int page, String keyword) {
		List<QdProduct> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_product where introduce like ? || faq like ? limit ?,?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setInt(3, (page - 1) * size);
			ps.setInt(4, size);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdProduct product = new QdProduct();
				product.setPid(rs.getInt("pid"));
				product.setUid(rs.getInt("uid"));
				product.setMid(rs.getInt("mid"));
				product.setPname(rs.getString("pname"));
				product.setMax(rs.getInt("max"));
				product.setMin(rs.getInt("min"));
				product.setRatetype(rs.getInt("ratetype"));
				product.setRate(rs.getFloat("rate"));
				product.setDeadline(rs.getTimestamp("deadline"));
				product.setClientlocation(rs.getInt("clientlocation"));
				product.setClient(rs.getInt("client"));
				product.setRepaymentmethod(rs.getInt("repaymentmethod"));
				product.setPtype(rs.getInt("ptype"));
				product.setUsesofloan(rs.getInt("usesofloan"));
				product.setIntroduce(rs.getString("introduce"));
				product.setProcesses(rs.getString("processes"));
				product.setApplication(rs.getString("application"));
				product.setFaq(rs.getString("faq"));
				product.setRNum(rs.getInt("r_num"));
				product.setPostdate(rs.getTimestamp("postdate"));
				
				list.add(product);
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
	public QdProduct getProduct(int pid) {
		QdProduct product = null;
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_product where pid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				product = new QdProduct();
				product.setPid(rs.getInt("pid"));
				product.setUid(rs.getInt("uid"));
				product.setMid(rs.getInt("mid"));
				product.setPname(rs.getString("pname"));
				product.setMax(rs.getInt("max"));
				product.setMin(rs.getInt("min"));
				product.setRatetype(rs.getInt("ratetype"));
				product.setRate(rs.getFloat("rate"));
				product.setDeadline(rs.getTimestamp("deadline"));
				product.setClientlocation(rs.getInt("clientlocation"));
				product.setClient(rs.getInt("client"));
				product.setRepaymentmethod(rs.getInt("repaymentmethod"));
				product.setPtype(rs.getInt("ptype"));
				product.setUsesofloan(rs.getInt("usesofloan"));
				product.setIntroduce(rs.getString("introduce"));
				product.setProcesses(rs.getString("processes"));
				product.setApplication(rs.getString("application"));
				product.setFaq(rs.getString("faq"));
				product.setRNum(rs.getInt("r_num"));
				product.setPostdate(rs.getTimestamp("postdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException(e.getMessage(), e);

		} finally {
			cJDBCUtilsSingleton.getInstance().free(rs, ps, conn);
		}
		return product;
	}

	@Override
	public List<QdProduct> getProductByUser(String username) {
		List<QdProduct> list = new LinkedList<>();
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "select * from QingDyDB.qd_product left outer join QingDyDB.qd_member on QingDyDB.qd_product.uid=QingDyDB.qd_member.uid where username=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QdProduct product = new QdProduct();
				product = new QdProduct();
				product.setPid(rs.getInt("pid"));
				product.setUid(rs.getInt("uid"));
				product.setMid(rs.getInt("mid"));
				product.setPname(rs.getString("pname"));
				product.setMax(rs.getInt("max"));
				product.setMin(rs.getInt("min"));
				product.setRatetype(rs.getInt("ratetype"));
				product.setRate(rs.getFloat("rate"));
				product.setDeadline(rs.getTimestamp("deadline"));
				product.setClientlocation(rs.getInt("clientlocation"));
				product.setClient(rs.getInt("client"));
				product.setRepaymentmethod(rs.getInt("repaymentmethod"));
				product.setPtype(rs.getInt("ptype"));
				product.setUsesofloan(rs.getInt("usesofloan"));
				product.setIntroduce(rs.getString("introduce"));
				product.setProcesses(rs.getString("processes"));
				product.setApplication(rs.getString("application"));
				product.setFaq(rs.getString("faq"));
				product.setRNum(rs.getInt("r_num"));
				product.setPostdate(rs.getTimestamp("postdate"));

				list.add(product);
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
	public int updateProduct(QdProduct product) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_product set pname,max,min,ratetype,rate,deadline,clientlocation,client,repaymentmethod,ptype,usesofloan,introduce,processes,application,faq where pid=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getPname());
			ps.setInt(2, product.getMax());
			ps.setInt(3, product.getMin());
			ps.setInt(4, product.getRatetype());
			ps.setFloat(5, product.getRate());
			ps.setTimestamp(6, product.getDeadline());
			ps.setInt(7, product.getClientlocation());
			ps.setInt(8, product.getClient());
			ps.setInt(9, product.getRepaymentmethod());
			ps.setInt(10, product.getPtype());
			ps.setInt(11, product.getUsesofloan());
			ps.setString(12, product.getIntroduce());
			ps.setString(13, product.getProcesses());
			ps.setString(14, product.getApplication());
			ps.setString(15, product.getFaq());
			ps.setInt(16, product.getPid());
			
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
	public int verifyProduct(int pid, int verify) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "update QingDyDB.qd_product set verify=? where pid=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, verify);
			ps.setInt(2, pid);
			
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
	public int removeProduct(int pid) {
		try {
			conn = cJDBCUtilsSingleton.getInstance().getConnection();

			sql = "delete from QingDyDB.qd_product where pid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			
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
