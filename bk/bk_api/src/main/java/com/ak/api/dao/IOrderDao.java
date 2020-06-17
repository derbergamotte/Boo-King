package com.ak.api.dao;

import com.ak.entities.Book;
import com.ak.entities.Order;
import com.ak.entities.User;

import java.util.List;

public interface IOrderDao extends IAGenericDao<Order>{

    List<Order> getByBook(Book book);

    List<Order> getByUser(User user);

    Order getByUserAndBook(User user, Book book);

    List<Order> getAllActualOrders();

}
