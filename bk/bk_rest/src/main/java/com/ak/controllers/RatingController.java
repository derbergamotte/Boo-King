package com.ak.controllers;

import com.ak.api.services.IRatingService;
import com.ak.dto.RatingDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class RatingController {

    @Autowired
    IRatingService ratingService;
    
    @GetMapping(value = "/books/{id}/ratings")
    public ModelAndView getBookRatings(@PathVariable long id) {
	return setModelAndViewParametrs(ratingService.getRatingsDtoByBook(id));
    }

    @GetMapping(value = "/users/{id}/ratings")
    public ModelAndView getUserRatings(@PathVariable long id) {
	return setModelAndViewParametrs(ratingService.getRatingsDtoByUser(id));
    }

    @GetMapping(value = "/users/user/ratings")
    public ModelAndView getCurrentUserRatings() {
	return setModelAndViewParametrs(ratingService.getRatingsDtoByUser());
    }

    @PostMapping(value = "/books/{id}/rate")
    public ModelAndView addRatingSubmit(@PathVariable long id, String comment, int rate) {
	ModelAndView modelAndView = new ModelAndView(new RedirectView("#result"));
	ratingService.addRating(id, comment, rate);
	return modelAndView;
    }
    
    private ModelAndView setModelAndViewParametrs(List<RatingDto> ratingList) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("ratingList", ratingList);
	modelAndView.setViewName("ratings");
	return modelAndView;
    }

}
