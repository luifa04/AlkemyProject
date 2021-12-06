
package com.alkemy.ong.service;


import com.alkemy.ong.exception.EmailException;
import com.alkemy.ong.exception.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IWelcomeTemplateService welcomeTemplateService;

    public void sendEmail(String to, String body, String subject) throws EmailException {

        if (to.isEmpty()) {
            throw new EmailException();
        }
        if (body.isEmpty()) {
            throw new EmailException();
        }
        if (subject.isEmpty()) {
            throw new EmailException();
        } else {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("oalkemy@gmail.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
            System.out.println("Sent Email...");
        }

    }

    public void sendWelcomeEmail(String to, String name, String surname) throws EmailExistException {


        String subject = welcomeTemplateService.getSubject();

        try {
            String template = welcomeTemplateService.setTemplate(name, surname);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("oalkemy@gmail.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(template);
            javaMailSender.send(simpleMailMessage);
            System.out.println("Sent Email...");

        } catch (EmailExistException e) {
            e.printStackTrace();
        }

    }

}

