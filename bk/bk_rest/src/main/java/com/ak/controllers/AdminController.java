package com.ak.controllers;

import com.ak.api.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IUserService userService;

    @GetMapping
    public ModelAndView getAllUsers() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("adminusers");
	modelAndView.addObject("userList", userService.getAllUsersDto());
	return modelAndView;
    }
}
