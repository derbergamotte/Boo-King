package com.ak.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ extends com.ak.entities.AEntity_ {

	public static volatile ListAttribute<Department, Book> books;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile ListAttribute<Department, Order> orders;

	public static final String BOOKS = "books";
	public static final String NAME = "name";
	public static final String ORDERS = "orders";

}

