package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ak.dto.BookDto;
import com.ak.entities.Book;
import com.ak.entities.Rating;

public class BookMapper {

    private BookMapper() {
    }

    public static List<BookDto> convertList(List<Book> entities) {
	List<BookDto> books = new ArrayList<>();
	for (Book entity : entities) {
	    BookDto dto = entityToDto(entity);
	    books.add(dto);
	}
	return books;
    }

    public static BookDto entityToDto(Book entity) {
	BookDto dto = new BookDto();
	dto.setId(entity.getId());
	dto.setBookdetail(BookdetailMapper.entityToDto(entity.getBookdetail()));
	dto.setListDepartment(DepartmentMapper.convertList(entity.getDepartments()).stream().collect(Collectors.toSet()));
	dto.setAverageRate(entity.getRatings().stream().mapToDouble(Rating::getRate).average().orElse(Double.NaN));
	return dto;
    }

}
