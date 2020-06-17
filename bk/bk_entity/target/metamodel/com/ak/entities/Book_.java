package com.ak.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ extends com.ak.entities.AEntity_ {

	public static volatile ListAttribute<Book, Rating> ratings;
	public static volatile SingularAttribute<Book, Bookdetail> bookdetail;
	public static volatile ListAttribute<Book, Order> orders;
	public static volatile ListAttribute<Book, Department> departments;

	public static final String RATINGS = "ratings";
	public static final String BOOKDETAIL = "bookdetail";
	public static final String ORDERS = "orders";
	public static final String DEPARTMENTS = "departments";

}

