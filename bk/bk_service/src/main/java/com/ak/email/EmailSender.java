package com.ak.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    private static final String ADMIN_EMAIL_ADDRESS = "GreatRogerWilko@gmail.com";

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendActivateEmail(String email, String activateCode) throws MessagingException {
	MimeMessage message = mailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message);
	String text = "http://127.0.0.1:8080/activate/" + activateCode;
	configureMimeMessageHelper(helper, ADMIN_EMAIL_ADDRESS, email, text, "Activate!");
	mailSender.send(message);
    }

    private void configureMimeMessageHelper(MimeMessageHelper helper, String mailFrom, String mailTo, String mailText,
	    String mailSubject) throws MessagingException {
	helper.setFrom(mailFrom);
	helper.setTo(mailTo);
	helper.setText(mailText, true);
	helper.setSubject(mailSubject);
    }

}
