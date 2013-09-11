package com.qingdy.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.qingdy.common.Constant;
import com.qingdy.dao.FavouriteDao;
import com.qingdy.model.Favourite;
import com.qingdy.model.UserDetail;

@Service("favouriteDao")
public class FavouriteDaoHibernate extends BaseDaoHibernate implements
		FavouriteDao {

	@Override
	public void addFavourite(Favourite favourite) {
		getHibernateTemplate().save(favourite);
	}

	@Override
	public void deleteFavourite(Long id) {
		Favourite favourite = getHibernateTemplate().get(Favourite.class, id);
		getHibernateTemplate().delete(favourite);
	}

	@Override
	public List<Favourite> getFavourites(String username) {
		UserDetail user = getHibernateTemplate().get(UserDetail.class, username);
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Favourite.class).add(Restrictions.eq("poster", user)));
	}

	@Override
	public Integer getFavouriteCount(Integer type, Long oid) {
		int count = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Constant.getClass(type)).add(Restrictions.eq("id", oid))).size();
		return count;
	}

	@Override
	public boolean isFavourite(Integer type, Long oid, String username) {
		UserDetail user = getHibernateTemplate().get(UserDetail.class, username);
		int count = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Constant.getClass(type)).add(Restrictions.eq("id", oid)).add(Restrictions.eq("poster", user))).size();
		return count == 1 ? true : false;
	}

}
