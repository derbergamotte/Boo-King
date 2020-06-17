package com.ak.controllers;

import java.io.IOException;
import java.util.List;

import exceptions.IsbnNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ak.api.services.IBookdetailService;
import com.ak.api.services.IDepartmentService;
import com.ak.api.services.IOrderService;
import com.ak.api.services.IBookService;
import com.ak.dto.BookDto;
import com.ak.dto.BookdetailDto;
import com.ak.dto.DepartmentDto;
import com.ak.dto.OrderDto;
import com.ak.dto.RatingDto;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    IBookService bookService;

    @Autowired
    IBookdetailService bookdetailsService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IDepartmentService departmentService;

    @GetMapping
    public ModelAndView getAllBooks() {
	ModelAndView modelAndView = new ModelAndView();
	List<BookDto> books = bookService.getAllBooksDto();
	modelAndView.setViewName("books");
	modelAndView.addObject("bookList", books);
	return modelAndView;
    }

    @GetMapping(value = "/addbook")
    public ModelAndView addBook() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("addbook");
	modelAndView.addObject("department", new DepartmentDto());
	modelAndView.addObject("bookdetail", new BookdetailDto());
	modelAndView.addObject("departments", departmentService.getAllDepartmentsDto());
	return modelAndView;
    }

    @PostMapping(value = "/addbook")
    public ModelAndView addBookSubmit(Long id, String isbn) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    bookService.addBook(isbn, id);
	    modelAndView.setView(new RedirectView("addbook#result"));
	} catch (IsbnNotFoundException e) {
	    modelAndView.setView(new RedirectView("addbook#error"));
	    log.error("ISBN not found");
	} catch (IOException e) {
	    modelAndView.setView(new RedirectView("addbook#error"));
	    log.error("HTML parser didn't to get datas", e);
	}
	return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getBook(@PathVariable long id) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    modelAndView.addObject("book", bookService.getBookDtoById(id));
	    modelAndView.addObject("istaken", orderService.isTakenBook(id));
	    modelAndView.addObject("order", new OrderDto());
	    modelAndView.addObject("rating", new RatingDto());
	    modelAndView.setViewName("book");
	} catch (NullPointerException e) {
	    modelAndView.setView(new RedirectView("#error"));
	}
	return modelAndView;
    }

    @GetMapping(value = "/{id}/update")
    public ModelAndView updateBook(@PathVariable long id) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("bookdetail", bookdetailsService.getBookdetailDtoById(id));
	modelAndView.setViewName("bookupdate");
	return modelAndView;
    }

    @PostMapping(value = "/{id}/update")
    public ModelAndView submitUpdateBook(@PathVariable long id, BookdetailDto bookdetailDto) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    bookdetailsService.updateBookdetail(id, bookdetailDto);
	    modelAndView.setView(new RedirectView("update#result"));
	} catch (DataIntegrityViolationException e) {
	    modelAndView.setView(new RedirectView("update#error"));
	}
	return modelAndView;
    }

}
