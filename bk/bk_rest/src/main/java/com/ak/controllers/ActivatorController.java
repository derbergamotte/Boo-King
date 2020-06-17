package com.ak.controllers;

import com.ak.api.services.IActivatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/activate")
public class ActivatorController {

    @Autowired
    IActivatorService activatorService;
    
    @GetMapping(value = "/{code}")
    public ModelAndView activation(@PathVariable String code) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    modelAndView.setViewName("activation");
	} catch (NullPointerException e) {
	    modelAndView.setView(new RedirectView("#error"));
	}
	return modelAndView;
    }

    @PostMapping(value = "/{code}")
    public ModelAndView submitActivation(@PathVariable String code) {
	ModelAndView modelAndView = new ModelAndView();
	activatorService.activation(code);
	return modelAndView;
    }
}
