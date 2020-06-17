package com.ak.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Activator.class)
public abstract class Activator_ extends com.ak.entities.AEntity_ {

	public static volatile SingularAttribute<Activator, String> code;
	public static volatile SingularAttribute<Activator, User> user;

	public static final String CODE = "code";
	public static final String USER = "user";

}

