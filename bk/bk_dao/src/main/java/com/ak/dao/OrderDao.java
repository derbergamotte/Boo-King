package com.ak.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ak.api.dao.IOrderDao;
import com.ak.entities.Book;
import com.ak.entities.Order;
import com.ak.entities.Order_;
import com.ak.entities.User;

@Repository
public class OrderDao extends AGenericDao<Order> implements IOrderDao {

    public OrderDao() {
	super(Order.class);
    }

    @Override
    public List<Order> getByBook(Book book) {
	return listResultByQuery(book, Order_.BOOK);
    }

    @Override
    public List<Order> getByUser(User user) {
	return listResultByQuery(user, Order_.USER);
    }

    @Override
    public Order getByUserAndBook(User user, Book book) {
	Order order = new Order();
	try {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
	    Root<Order> root = query.from(Order.class);
	    Predicate[] predicates = new Predicate[3];
	    predicates[0] = criteriaBuilder.equal(root.get(Order_.USER), user);
	    predicates[1] = criteriaBuilder.equal(root.get(Order_.BOOK), book);
	    predicates[2] = criteriaBuilder.isNull(root.get(Order_.DATE_OF_RETURN_BOOK));
	    query.select(root).where(predicates);
	    TypedQuery<Order> result = entityManager.createQuery(query);
	    order = result.getSingleResult();
	} catch (NoResultException e) {
	    order = null;
	}
	return order;
    }

    @Override
    public List<Order> getAllActualOrders() {
	List<Order> orders = new ArrayList<>();
	try {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
	    Root<Order> root = query.from(Order.class);
	    query.select(root).where(criteriaBuilder.isNull(root.get(Order_.DATE_OF_RETURN_BOOK)));
	    TypedQuery<Order> result = entityManager.createQuery(query);
	    orders = result.getResultList();
	} catch (NoResultException e) {
	    orders = null;
	}
	return orders;
    }

}
