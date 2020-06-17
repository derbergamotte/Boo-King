package com.ak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ak.api.services.IUserService;
import com.ak.dto.UserDto;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping
    public ModelAndView getAllUsers() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("users");
	modelAndView.addObject("userList", userService.getAllUsersDto());
	return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getUser(@PathVariable long id) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("user");
	modelAndView.addObject("user", userService.getUserDtoById(id));
	return modelAndView;
    }

    @GetMapping(value = "/user")
    public ModelAndView getCurrentUser() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("currentuser");
	modelAndView.addObject("user", userService.getCurrentUser());
	return modelAndView;
    }

    @GetMapping(value = "/user/update")
    public ModelAndView updateUser() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("userdto", new UserDto());
	modelAndView.setViewName("userupdate");
	return modelAndView;
    }

    @PostMapping(value = "/user/update")
    public ModelAndView updateUserSubmit(UserDto userDto) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    userService.updateUser(userDto);
	    modelAndView.setView(new RedirectView("update#result"));
	} catch (DataIntegrityViolationException  e) {
	    modelAndView.setView(new RedirectView("update#error"));
	}
	return modelAndView;
    }
    
    @PostMapping(value = "/user/delete")
    public ModelAndView deleteUser() {
	ModelAndView modelAndView = new ModelAndView(new RedirectView("../../logout"));
	userService.delete();
	return modelAndView;
    }

}
