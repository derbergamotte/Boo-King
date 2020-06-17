package com.ak.services;

import javax.transaction.Transactional;

import exceptions.IsbnNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import com.ak.api.dao.IBookdetailDao;
import com.ak.api.services.IBookdetailService;
import com.ak.dto.BookdetailDto;
import com.ak.entities.Bookdetail;
import com.ak.mappers.BookdetailMapper;
import com.ak.utils.web.DataScrapper;

@Service
@Transactional
public class BookdetailService implements IBookdetailService {

    @Autowired
    private IBookdetailDao bookdetailsDao;

    @Autowired
    private DataScrapper dataScrapper;

    @Override
    public Bookdetail addBookdetail(String isbn) throws IsbnNotFoundException, IOException {
	Bookdetail bookdetails = dataScrapper.getBookFromWeb(isbn);
	return bookdetailsDao.create(bookdetails);
    }

    @Override
    public BookdetailDto getBookdetailDtoById(long id) {
	return BookdetailMapper.entityToDto(bookdetailsDao.getById(id));
    }

    @Override
    public Bookdetail getBookdetailByIsbn(String isbn) {
	return bookdetailsDao.getByIsbn(isbn);
    }

    @Override
    public void updateBookdetail(long id, BookdetailDto bookdetailDto) {
	Bookdetail entity = bookdetailsDao.getById(id);
	if (StringUtils.isNotEmpty(bookdetailDto.getTitle())) {
	    entity.setTitle(bookdetailDto.getTitle());
	}
	if (StringUtils.isNotEmpty(bookdetailDto.getAuthor())) {
	    entity.setAuthor(bookdetailDto.getAuthor());
	}
	if (StringUtils.isNotEmpty(bookdetailDto.getPublisher())) {
	    entity.setPublisher(bookdetailDto.getPublisher());
	}
	if (StringUtils.isNotEmpty(bookdetailDto.getDescription())) {
	    entity.setDescription(bookdetailDto.getDescription());
	}
	bookdetailsDao.update(entity);
    }

    @Override
    public void delete(long id) {
	bookdetailsDao.delete(bookdetailsDao.getById(id));
    }

}
