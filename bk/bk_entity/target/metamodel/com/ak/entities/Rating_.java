package com.ak.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rating.class)
public abstract class Rating_ extends com.ak.entities.AEntity_ {

	public static volatile SingularAttribute<Rating, Integer> rate;
	public static volatile SingularAttribute<Rating, Book> book;
	public static volatile SingularAttribute<Rating, String> comment;
	public static volatile SingularAttribute<Rating, User> user;

	public static final String RATE = "rate";
	public static final String BOOK = "book";
	public static final String COMMENT = "comment";
	public static final String USER = "user";

}

