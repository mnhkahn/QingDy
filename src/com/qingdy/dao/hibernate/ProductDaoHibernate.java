package com.qingdy.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.cyeam.util.StringUtil;
import com.qingdy.common.cRestrictions;
import com.qingdy.dao.ProductDao;
import com.qingdy.model.Blog;
import com.qingdy.model.Loan;
import com.qingdy.model.Product;
import com.qingdy.model.UserDetail;

@Service("productDao")
public class ProductDaoHibernate extends BaseDaoHibernate implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		getHibernateTemplate().saveOrUpdate(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Product.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}
	
	@Override
	public List<Product> getProducts(int size, int page, String[] field,
			String[] value, String operator[], String sidx, String sord, boolean verify) {
		return getHibernateTemplate().findByCriteria(cRestrictions.getRestrictions(Product.class, field, value, operator, sidx, sord, verify), size * (page - 1), size);
	}

	@Override
	public Product getProduct(Long id) {
		Product product = getHibernateTemplate().get(Product.class, id);
		if (product == null) {
			throw new ObjectRetrievalFailureException(Product.class, id);
		}
		return product;
	}

	@Override
	public List<Product> getProduct(String username) {
		UserDetail poster = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Product.class).add(Restrictions.ge("poster", poster)));
	}

	@Override
	public void verifyProduct(Long id, boolean verify) {
		Product product = getProduct(id);
		if (verify) {
			product.setVerify(1);
		}
		else {
			product.setVerify(0);
		}
		saveProduct(product);
	}

	@Override
	public void removeProduct(Long id) {
		Product product = getProduct(id);
		getHibernateTemplate().delete(product);
	}

}
