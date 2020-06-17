package com.ak.entities;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends com.ak.entities.AEntity_ {

	public static volatile SingularAttribute<Order, Calendar> dateOfTakeBook;
	public static volatile SingularAttribute<Order, Calendar> dateOfReturnBook;
	public static volatile SingularAttribute<Order, Book> book;
	public static volatile SingularAttribute<Order, Department> department;
	public static volatile SingularAttribute<Order, User> user;

	public static final String DATE_OF_TAKE_BOOK = "dateOfTakeBook";
	public static final String DATE_OF_RETURN_BOOK = "dateOfReturnBook";
	public static final String BOOK = "book";
	public static final String DEPARTMENT = "department";
	public static final String USER = "user";

}

