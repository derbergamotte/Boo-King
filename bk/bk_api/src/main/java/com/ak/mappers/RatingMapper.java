package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ak.dto.RatingDto;
import com.ak.entities.Rating;

public final class RatingMapper {

    private RatingMapper() {
    }

    public static List<RatingDto> convertList(List<Rating> entities) {
	List<RatingDto> atings = new ArrayList<>();
	for (Rating entity : entities) {
	    RatingDto dto = entityToDto(entity);
	    atings.add(dto);
	}
	return atings;
    }

    public static RatingDto entityToDto(Rating entity) {
	RatingDto dto = new RatingDto();
	dto.setId(entity.getId());
	dto.setUserName(entity.getUser().getUsername());
	dto.setBookName(entity.getBook().getBookdetail().getTitle() + " by " + entity.getBook().getBookdetail().getAuthor());
	dto.setComment(entity.getComment());
	dto.setRate(entity.getRate());
	return dto;
    }

}
