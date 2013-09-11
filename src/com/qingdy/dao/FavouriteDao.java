package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.Favourite;

public interface FavouriteDao extends DAO {

	public void addFavourite(Favourite favourite);
	
	public void deleteFavourite(Long id);
	
	public List<Favourite> getFavourites(String username);
	
	public Integer getFavouriteCount(Integer type, Long oid);
	
	public boolean isFavourite(Integer type, Long oid, String username);
}
