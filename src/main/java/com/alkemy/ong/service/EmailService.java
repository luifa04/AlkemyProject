
package com.alkemy.ong.service;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailService {

    private static final SendGrid sendGrid = new SendGrid("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");
    private static final Email from = new Email("oalkemy@gmail.com");


    public void sendMail(String emailTo, String subject, String emailContent) throws IOException {

        Email to = new Email(emailTo);
        Content content = new Content("text/plain", emailContent);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
        }
    }
}

