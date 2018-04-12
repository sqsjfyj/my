package com.edu.dao;

import java.util.List;

public interface IBaseDao<T> {
	int insert(T o);
	
	int insertList(List<T> list);
	
	int update(T o);
	
	int deleteList(Class<T> c, int... ids);
	
	int delete(T o);
	
	int delete(Class<T> c, int id);
	
	T findById(Class<T> c, int id);
	
	T findOne(String hql, String[] param);
	
	List<T> find(String hql, String[] param);
	
	List<T> findPage(String hql, String[] param, int page, int size);
	
	int getCount(String hql, String[] pras);
	
	List<T> findByFields(String hql, String fields[], String condition);
}
