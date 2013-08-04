package com.qingdy.dao;

import java.util.List;

import com.qingdy.model.QdFavourite;

public interface FavouriteDao {

	public int addFavourite(QdFavourite favourite);
	
	public List<QdFavourite> getFavouriteList(int size, int page);
	
	public int updateFavourite(QdFavourite favourite);
	
	public int removeFavourite(int fid);
}
