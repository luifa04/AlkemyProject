package com.alkemy.ong.service;

import com.alkemy.ong.exception.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class WelcomeTemplateService {

    @Autowired
    private Configuration config;


    public String setTemplate(String name, String surName) throws EmailException {
        Map<String, Object> model = new HashMap<>();

        model.put("name", name);
        model.put("surname", surName);

        try {

            Template t = config.getTemplate("email-template.ftl");

            return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        }catch (TemplateException | IOException e){
            String messageError = "Welcome Email creating failure : " + e.getMessage();
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

            throw new EmailException(messageError, status);

        }
    }

    public String getSubject(){

        return "Email de Bienvenida";
    }


}