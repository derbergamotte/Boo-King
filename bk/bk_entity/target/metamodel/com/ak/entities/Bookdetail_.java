package com.ak.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bookdetail.class)
public abstract class Bookdetail_ extends com.ak.entities.AEntity_ {

	public static volatile SingularAttribute<Bookdetail, String> cover;
	public static volatile SingularAttribute<Bookdetail, String> author;
	public static volatile SingularAttribute<Bookdetail, String> isbn;
	public static volatile SingularAttribute<Bookdetail, Book> book;
	public static volatile SingularAttribute<Bookdetail, String> description;
	public static volatile SingularAttribute<Bookdetail, String> publisher;
	public static volatile SingularAttribute<Bookdetail, String> title;

	public static final String COVER = "cover";
	public static final String AUTHOR = "author";
	public static final String ISBN = "isbn";
	public static final String BOOK = "book";
	public static final String DESCRIPTION = "description";
	public static final String PUBLISHER = "publisher";
	public static final String TITLE = "title";

}

