package com.ak.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.api.dao.IOrderDao;
import com.ak.api.services.IOrderService;
import com.ak.api.services.IBookService;
import com.ak.api.services.IDepartmentService;
import com.ak.api.services.IUserService;
import com.ak.dto.OrderDto;
import com.ak.entities.Order;
import com.ak.mappers.OrderMapper;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDepartmentService departmenService;

    @Override
    public List<OrderDto> getAllOrdersDto() {
	return OrderMapper.convertList(orderDao.getAll());
    }

    @Override
    public List<OrderDto> getActualOrdersDto(){
	return OrderMapper.convertList(orderDao.getAllActualOrders());
    }

    @Override
    public List<OrderDto> getOrdersDtoByBook(long bookId){
	return OrderMapper.convertList(orderDao.getByBook(bookService.getBookById(bookId)));
    }

    @Override
    public List<OrderDto> getOrdersDtoByUser(long userId){
	return OrderMapper.convertList(orderDao.getByUser(userService.getUserById(userId)));
    }

    @Override
    public List<OrderDto> getOrdersDtoByUser(){
	return OrderMapper.convertList(orderDao.getByUser(userService.getCurrentUser()));
    }
    
    @Override
    public void takeBook(long bookId, long departmentId) {
	Order order = new Order();
	order.setBook(bookService.getBookById(bookId));
	order.setUser(userService.getCurrentUser());
	order.setDateOfTakeBook(Calendar.getInstance());
	order.setDepartment(departmenService.getDepartmentById(departmentId));
	bookService.removeBookInDepartment(bookId, departmentId);
	orderDao.create(order);
    }

    @Override
    public void returnBook(long bookId) {
	Order order = orderBook(bookId);
	order.setDateOfReturnBook(Calendar.getInstance());
	bookService.addBookInDepartment(bookId, order.getDepartment().getId());
	orderDao.update(order);
    }

    @Override
    public boolean isTakenBook(long bookId) {
	return Optional.ofNullable(orderBook(bookId)).isPresent();
    }

    private Order orderBook(long bookId) {
	return orderDao.getByUserAndBook(userService.getCurrentUser(), bookService.getBookById(bookId));
    }

}
