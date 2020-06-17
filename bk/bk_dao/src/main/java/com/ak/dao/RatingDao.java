package com.ak.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ak.api.dao.IRatingDao;
import com.ak.entities.Book;
import com.ak.entities.Rating;
import com.ak.entities.Rating_;
import com.ak.entities.User;

@Repository
public class RatingDao extends AGenericDao<Rating> implements IRatingDao {

    public RatingDao() {
	super(Rating.class);
    }

    @Override
    public List<Rating> getByBook(Book book) {
	return listResultByQuery(book, Rating_.BOOK);
    }

    @Override
    public List<Rating> getByUser(User user) {
	return listResultByQuery(user, Rating_.USER);
    }

    @Override
    public Rating getByUserAndBook(User user, Book book) {
	Rating rating = new Rating();
	try {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Rating> query = criteriaBuilder.createQuery(Rating.class);
	    Root<Rating> root = query.from(Rating.class);
	    Predicate[] predicates = new Predicate[2];
	    predicates[0] = criteriaBuilder.equal(root.get(Rating_.USER), user);
	    predicates[1] = criteriaBuilder.equal(root.get(Rating_.BOOK), book);
	    query.select(root).where(predicates);
	    TypedQuery<Rating> result = entityManager.createQuery(query);
	    rating = result.getSingleResult();
	} catch (NoResultException e) {
	    rating = null;
	}
	return rating;
    }

}
