package com.ak.controllers;

import com.ak.api.services.IOrderService;
import com.ak.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @GetMapping(value = "/orders")
    public ModelAndView getAllOrders() {
	return setModelAndViewParametrs(orderService.getAllOrdersDto());
    }
    
    @GetMapping(value = "/actualorders")
    public ModelAndView getActualOrders() {
	return setModelAndViewParametrs(orderService.getActualOrdersDto());
    }
    
    @GetMapping(value = "/books/{id}/orders")
    public ModelAndView getBookOrders(@PathVariable long id) {
	return setModelAndViewParametrs(orderService.getOrdersDtoByBook(id));
    }

    @GetMapping(value = "/users/{id}/orders")
    public ModelAndView getUserOrders(@PathVariable long id) {
	return setModelAndViewParametrs(orderService.getOrdersDtoByUser(id));
    }

    @GetMapping(value = "/users/user/orders")
    public ModelAndView getCurrentUserOrders() {
	return setModelAndViewParametrs(orderService.getOrdersDtoByUser());
    }

    @PostMapping(value = "/books/{id}/return")
    public ModelAndView bookReturn(@PathVariable long id) {
	ModelAndView modelAndView = new ModelAndView(new RedirectView("#return"));
	orderService.returnBook(id);
	return modelAndView;
    }
    
    @PostMapping(value = "/books/{id}/take")
    public ModelAndView bookTake(@PathVariable long id, long departmentId) {
	ModelAndView modelAndView = new ModelAndView(new RedirectView("#take"));
	orderService.takeBook(id, departmentId);
	return modelAndView;
    }
    
    private ModelAndView setModelAndViewParametrs(List<OrderDto> orderList) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("orderList", orderList);
	modelAndView.setViewName("orders");
	return modelAndView;
    }

}
