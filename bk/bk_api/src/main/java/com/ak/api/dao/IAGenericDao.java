package com.ak.api.dao;

import java.util.List;

import com.ak.entities.AEntity;

public interface IAGenericDao<T extends AEntity> {
	Class<T> getGenericClass();
	
	T create(T entity);
	
	T getById(long id);
	
	void update (T entity);
	
	void delete (T entity);
	
	List<T> getAll();
}
