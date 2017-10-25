package com.sam.controller;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
 
@Controller
public class MailController {
 
    @Autowired
    private JavaMailSender mailSender;
    @RequestMapping(value="loadEmail",method = RequestMethod.GET)
    public String loadEmail(){
    	return "EmailForm";
    }
    @RequestMapping(value="sendEmail",method = RequestMethod.GET)
    public String doSendEmail(HttpServletRequest request) {
    	String recipientAddress = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        
        // sends the e-mail
        mailSender.send(email);
        
        // forwards to the view named "Result"
        return "Result";
    }
}
