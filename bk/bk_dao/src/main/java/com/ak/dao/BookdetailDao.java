package com.ak.dao;

import org.springframework.stereotype.Repository;

import com.ak.api.dao.IBookdetailDao;
import com.ak.entities.Bookdetail;
import com.ak.entities.Bookdetail_;

@Repository
public class BookdetailDao extends AGenericDao<Bookdetail> implements IBookdetailDao {

    public BookdetailDao() {
	super(Bookdetail.class);
    }

    @Override
    public Bookdetail getByIsbn(String isbn) {
	return singleResultByQuery(isbn, Bookdetail_.ISBN);
    }

}
