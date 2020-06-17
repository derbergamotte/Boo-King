package com.ak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;

import com.ak.api.services.IUserService;
import com.ak.dto.UserDto;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    IUserService userService;

    @GetMapping
    public ModelAndView home() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("user", userService.getCurrentUser());
	modelAndView.setViewName("index");
	return modelAndView;
    }

    @GetMapping(value = "/signup")
    public ModelAndView signup() {
	ModelAndView modelAndView = new ModelAndView();
	UserDto userDto = new UserDto();
	modelAndView.addObject("dto", userDto);
	modelAndView.setViewName("signup");
	return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView singupSubmit(UserDto dto) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    userService.addUser(dto);
	    modelAndView.setView(new RedirectView("signup#result"));
	} catch (DataIntegrityViolationException  e) {
	    modelAndView.setView(new RedirectView("signup#error"));
	    log.error("User is exist", e);
	}
	return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("login");
	return modelAndView;
    }

    @GetMapping(value = "/bye")
    public ModelAndView bye() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("bye");
	return modelAndView;
    }

    @GetMapping(value = "/403")
    public ModelAndView error() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("user", userService.getCurrentUser());
	modelAndView.setViewName("403");
	return modelAndView;
    }

}
