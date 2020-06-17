package com.ak.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.ak.entities.AEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, Rating> ratings;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile ListAttribute<User, Order> orders;
	public static volatile SingularAttribute<User, Activator> activator;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD = "password";
	public static final String RATINGS = "ratings";
	public static final String ROLES = "roles";
	public static final String ORDERS = "orders";
	public static final String ACTIVATOR = "activator";
	public static final String EMAIL = "email";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

