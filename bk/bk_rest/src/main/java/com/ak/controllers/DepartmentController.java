package com.ak.controllers;

import com.ak.api.services.IDepartmentService;
import com.ak.dto.DepartmentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentController {
    
    private static final String ERROR = "#error";
    private static final String RESULT = "#result";

    @Autowired
    IDepartmentService departmentService;
    
    @GetMapping
    public ModelAndView getAllDepartments() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("departments", departmentService.getAllDepartmentsDto());
	modelAndView.addObject("department", new DepartmentDto());
	modelAndView.setViewName("departments");
	return modelAndView;
    }

    @PostMapping(value = "/adddepartment")
    public ModelAndView submitAddDepartment(DepartmentDto department) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    departmentService.addDepartment(department);
	    modelAndView.setView(new RedirectView(RESULT));
	} catch (DataIntegrityViolationException e) {
	    modelAndView.setView(new RedirectView(ERROR));
	}
	return modelAndView;
    }

    @GetMapping(value = "/{id}/update")
    public ModelAndView renameDepartment(@PathVariable long id) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("department", departmentService.getDepartmentById(id));
	modelAndView.setViewName("departmentupdate");
	return modelAndView;
    }

    @PostMapping(value = "/{id}/update")
    public ModelAndView submitRenameDepartment(@PathVariable long id, DepartmentDto department, String name) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    departmentService.updateDepartment(id, department);
	    modelAndView.setView(new RedirectView("../" + RESULT));
	} catch (DataIntegrityViolationException e) {
	    modelAndView.setView(new RedirectView("../" + ERROR));
	}
	return modelAndView;
    }
    
    @PostMapping(value = "/{id}delete")
    public ModelAndView deleteDepartment(@PathVariable long id) {
	ModelAndView modelAndView = new ModelAndView();
	try {
	    departmentService.delete(id);
	    modelAndView.setView(new RedirectView(RESULT));
	} catch (DataIntegrityViolationException e) {
	    modelAndView.setView(new RedirectView(ERROR));
	}
	return modelAndView;
    }

}
