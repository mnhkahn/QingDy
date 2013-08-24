package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.qingdy.dao.ProductDao;
import com.qingdy.model.Product;

@Service("productDao")
public class ProductDaoHibernate extends BaseDaoHibernate implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		getHibernateTemplate().saveOrUpdate(product);
	}

	@Override
	public List<Product> getProducts(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify) {
		// TODO Auto-generated method stub
		return null;
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
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Product.class).add(Restrictions.ge("poster", username)));
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
