package com.ak.dao;

import org.springframework.stereotype.Repository;

import com.ak.api.dao.IBookDao;
import com.ak.entities.Book;

@Repository
public class BookDao extends AGenericDao<Book> implements IBookDao {

    public BookDao() {
	super(Book.class);
    }

}
