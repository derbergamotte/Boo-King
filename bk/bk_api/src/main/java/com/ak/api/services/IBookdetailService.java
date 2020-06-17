package com.ak.api.services;

import com.ak.dto.BookdetailDto;
import com.ak.entities.Bookdetail;

import java.io.IOException;

import exceptions.IsbnNotFoundException;

public interface IBookdetailService {

    Bookdetail addBookdetail(String isbn) throws IsbnNotFoundException, IOException;

    BookdetailDto getBookdetailDtoById(long id);

    Bookdetail getBookdetailByIsbn(String isbn);

    void updateBookdetail(long id, BookdetailDto dto);

    void delete(long id);

}
