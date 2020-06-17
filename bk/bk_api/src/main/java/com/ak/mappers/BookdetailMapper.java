package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ak.dto.BookdetailDto;
import com.ak.entities.Bookdetail;

public class BookdetailMapper {

    private BookdetailMapper() {
    }

    public static List<BookdetailDto> convertList(List<Bookdetail> entities) {
	List<BookdetailDto> books = new ArrayList<>();
	for (Bookdetail entity : entities) {
	    BookdetailDto dto = entityToDto(entity);
	    books.add(dto);
	}
	return books;
    }

    public static BookdetailDto entityToDto(Bookdetail entity) {
	BookdetailDto dto = new BookdetailDto();
	dto.setId(entity.getId());
	dto.setTitle(entity.getTitle());
	dto.setAuthor(entity.getAuthor());
	dto.setDescription(entity.getDescription());
	dto.setCover(entity.getCover());
	dto.setPublisher(entity.getPublisher());
	dto.setIsbn(entity.getIsbn());
	return dto;
    }

    public static Bookdetail dtoToEntity(BookdetailDto dto) {
	Bookdetail entity = new Bookdetail();
	entity.setId(dto.getId());
	entity.setTitle(dto.getTitle());
	entity.setAuthor(dto.getAuthor());
	entity.setDescription(dto.getDescription());
	entity.setCover(dto.getCover());
	entity.setPublisher(dto.getPublisher());
	entity.setIsbn(dto.getIsbn());
	return entity;
    }

}
