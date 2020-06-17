package com.ak.api.dao;

import com.ak.entities.Book;
import com.ak.entities.Rating;
import com.ak.entities.User;

import java.util.List;

public interface IRatingDao extends IAGenericDao<Rating>{

    public List<Rating> getByBook(Book book);

    public List<Rating> getByUser(User user);

    Rating getByUserAndBook(User user, Book book);

}
