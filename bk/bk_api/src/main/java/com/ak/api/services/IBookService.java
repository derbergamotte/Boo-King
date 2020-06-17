package com.ak.api.services;

import java.io.IOException;
import java.util.List;

import exceptions.IsbnNotFoundException;

import com.ak.dto.BookDto;
import com.ak.entities.Book;

public interface IBookService {

    List<BookDto> getAllBooksDto();

    void addBook(String isbn, long departmentId) throws IsbnNotFoundException, IOException;

    Book getBookById(long bookId);

    BookDto getBookDtoById(long bookId);

    void removeBookInDepartment(long bookId, long departmentId);

    void addBookInDepartment(long bookId, long departmentId);

}
