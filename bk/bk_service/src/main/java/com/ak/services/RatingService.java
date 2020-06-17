package com.ak.services;

import com.ak.api.dao.IRatingDao;
import com.ak.api.services.IBookService;
import com.ak.api.services.IRatingService;
import com.ak.api.services.IUserService;
import com.ak.dto.RatingDto;
import com.ak.entities.Rating;
import com.ak.mappers.RatingMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class RatingService implements IRatingService {

    @Autowired
    IRatingDao ratingDao;

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;

    @Override
    public List<RatingDto> getRatingsDtoByBook(long bookId) {
	return RatingMapper.convertList(ratingDao.getByBook(bookService.getBookById(bookId)));
    }

    @Override
    public List<RatingDto> getRatingsDtoByUser(long userId) {
	return RatingMapper.convertList(ratingDao.getByUser(userService.getUserById(userId)));
    }

    @Override
    public List<RatingDto> getRatingsDtoByUser() {
	return RatingMapper.convertList(ratingDao.getByUser(userService.getCurrentUser()));
    }

    @Override
    public void addRating(long bookId, String comment, int rate) {
	if (!isRateBook(bookId)) {
	    Rating rating = new Rating();
	    rating.setUser(userService.getCurrentUser());
	    rating.setBook(bookService.getBookById(bookId));
	    rating.setComment(comment);
	    rating.setRate(rate);
	    ratingDao.create(rating);
	} else {
	    Rating rating = getRatingByUserAndBook(bookId);
	    rating.setComment(comment);
	    rating.setRate(rate);
	    ratingDao.update(rating);
	}
    }

    private boolean isRateBook(long bookId) {
	return Optional.ofNullable(getRatingByUserAndBook(bookId)).isPresent();
    }

    private Rating getRatingByUserAndBook(long bookId) {
	return ratingDao.getByUserAndBook(userService.getCurrentUser(), bookService.getBookById(bookId));
    }

}
