package com.ak.api.services;

import java.util.List;

import com.ak.dto.OrderDto;

public interface IOrderService {

    List<OrderDto> getAllOrdersDto();

    List<OrderDto> getActualOrdersDto();

    List<OrderDto> getOrdersDtoByBook(long bookId);

    List<OrderDto> getOrdersDtoByUser(long userId);

    List<OrderDto> getOrdersDtoByUser();

    void takeBook(long bookId, long departmentId);

    void returnBook(long bookId);

    boolean isTakenBook(long bookId);

}
