package com.ak.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import exceptions.IsbnNotFoundException;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.api.dao.IBookDao;
import com.ak.api.services.IBookdetailService;
import com.ak.api.services.IDepartmentService;
import com.ak.api.services.IBookService;
import com.ak.dto.BookDto;
import com.ak.entities.Book;
import com.ak.entities.Bookdetail;
import com.ak.entities.Department;
import com.ak.mappers.BookMapper;

@Service
@Transactional
public class BookService implements IBookService {

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IBookdetailService bookdetailService;

    @Autowired
    private IDepartmentService departmentService;

    @Override
    public List<BookDto> getAllBooksDto() {
	return BookMapper.convertList(bookDao.getAll());
    }

    @Override
    public void addBook(String isbn, long departmentId) throws IsbnNotFoundException, IOException {
	isbn = RegExUtils.replaceAll(isbn, "-", StringUtils.EMPTY);
	isbn = RegExUtils.replaceAll(isbn, " ", StringUtils.EMPTY);
	if (isbn.matches("\\d+") && isbn.length() == 13) {
	    Bookdetail bookdetail = bookdetailService.getBookdetailByIsbn(isbn);
	    if (bookdetail == null) {
		addNewBook(isbn, departmentId);
	    } else {
		addBookInDepartment(bookdetail.getId(), departmentId);
	    }
	} else {
	    throw new IsbnNotFoundException();
	}
    }

    @Override
    public Book getBookById(long id) {
	return bookDao.getById(id);
    }

    @Override
    public BookDto getBookDtoById(long id) {
	return BookMapper.entityToDto(getBookById(id));
    }

    @Override
    public void removeBookInDepartment(long bookId, long departmentId) {
	Book book = getBookById(bookId);
	book.getDepartments().remove(departmentService.getDepartmentById(departmentId));
    }

    @Override
    public void addBookInDepartment(long id, long departmentId) {
	Book book = getBookById(id);
	List<Department> departments = book.getDepartments();
	departments.add(departmentService.getDepartmentById(departmentId));
	book.setDepartments(departments);
	bookDao.update(book);
    }

    private void addNewBook(String isbn, long departmentId) throws IsbnNotFoundException, IOException {
	Book book = new Book();
	book.setBookdetail(bookdetailService.addBookdetail(isbn));
	List<Department> departments = new ArrayList<>();
	departments.add(departmentService.getDepartmentById(departmentId));
	book.setDepartments(departments);
	bookDao.create(book);
    }

}
