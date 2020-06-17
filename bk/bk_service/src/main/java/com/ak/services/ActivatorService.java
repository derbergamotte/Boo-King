package com.ak.services;

import com.ak.api.dao.IActivatorDao;
import com.ak.api.services.IActivatorService;
import com.ak.api.services.IUserService;
import com.ak.email.EmailSender;
import com.ak.entities.Activator;
import com.ak.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ActivatorService implements IActivatorService{

    @Autowired
    private IActivatorDao activatorDao;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private EmailSender emailSender; 
    
    @Override
    public void addActivator(User user, String email) {
	try {
	Activator activator = new Activator();
	String code = generatedCode();
	activator.setUser(user);
	activator.setCode(code);
	activatorDao.create(activator);
	    emailSender.sendActivateEmail(email, code);
	} catch (MessagingException e) {
	    log.error(e.getMessage());
	}
    }

    @Override
    public void activation(String code) {
	userService.setEnabled(activatorDao.getActivatorByCode(code).getUser().getId(), true);
    }

    private String generatedCode() {
	String code = "aaa"; 
	return code;
    }
    
}
