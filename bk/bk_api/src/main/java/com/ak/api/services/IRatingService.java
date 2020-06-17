package com.ak.api.services;

import com.ak.dto.RatingDto;

import java.util.List;

public interface IRatingService {

    List<RatingDto> getRatingsDtoByBook(long bookId);

    List<RatingDto> getRatingsDtoByUser(long userId);

    List<RatingDto> getRatingsDtoByUser();

    void addRating(long bookId, String comment, int rate);

}
