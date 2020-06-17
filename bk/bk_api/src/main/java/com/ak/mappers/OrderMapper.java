package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ak.dto.OrderDto;
import com.ak.entities.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static List<OrderDto> convertList(List<Order> entities) {
	List<OrderDto> orders = new ArrayList<>();
	for (Order entity : entities) {
	    OrderDto dto = entityToDto(entity);
	    orders.add(dto);
	}
	return orders;
    }

    public static OrderDto entityToDto(Order entity) {
	OrderDto dto = new OrderDto();
	dto.setId(entity.getId());
	dto.setUserName(entity.getUser().getUsername());
	dto.setBookName(entity.getBook().getBookdetail().getTitle() + " by " + entity.getBook().getBookdetail().getAuthor());
	dto.setDepartmentId(entity.getDepartment().getId());
	dto.setDepartmentName(entity.getDepartment().getName());
	dto.setDateOfTakeBook(entity.getDateOfTakeBook());
	dto.setDateOfReturnBook(entity.getDateOfReturnBook());
	return dto;
    }

}
