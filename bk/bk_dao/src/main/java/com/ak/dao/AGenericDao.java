package com.ak.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lombok.extern.slf4j.Slf4j;

import com.ak.api.dao.IAGenericDao;
import com.ak.entities.AEntity;

@Slf4j
public abstract class AGenericDao<T extends AEntity> implements IAGenericDao<T> {

    private Class<T> clazz;

    public AGenericDao(Class<T> clazz) {
	this.clazz = clazz;
    }

    public Class<T> getGenericClass() {
	return this.clazz;
    }

    @PersistenceContext
    protected EntityManager entityManager;

    public T create(T entity) {
	entityManager.persist(entity);
	return entity;
    }

    public T getById(long id) {
	return entityManager.find(getGenericClass(), id);
    }

    public void update(T entity) {
	entityManager.merge(entity);
	entityManager.flush();
    }

    public void delete(T entity) {
	entityManager.remove(entity);
    }

    public List<T> getAll() {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<T> query = criteriaBuilder.createQuery(getGenericClass());
	Root<T> root = query.from(getGenericClass());
	query.select(root);
	TypedQuery<T> result = entityManager.createQuery(query);
	return result.getResultList();
    }

    protected <E> T singleResultByQuery(E queryObject, String metaModel) {
	T object = null;
	try {
	    object = resultByQuery(queryObject, metaModel).getSingleResult();
	} catch (NoResultException e) {
	    log.error(e.getMessage());
	}
	return object;
    }

    protected <E> List<T> listResultByQuery(E queryObject, String metaModel) {
	List<T> objects = new ArrayList<>();
	try {
	    objects = resultByQuery(queryObject, metaModel).getResultList();
	} catch (NoResultException e) {
	    log.error(e.getMessage());
	}
	return objects;
    }

    private <E> TypedQuery<T> resultByQuery(E queryObject, String metaModel) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<T> query = criteriaBuilder.createQuery(getGenericClass());
	Root<T> root = query.from(getGenericClass());
	query.select(root).where(criteriaBuilder.equal(root.get(metaModel), queryObject));
	return entityManager.createQuery(query);
    }

}
