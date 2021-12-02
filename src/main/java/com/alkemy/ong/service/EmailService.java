
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

    static SendGrid sendGrid = new SendGrid("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");
    static Email from = new Email("ok@ong.com");

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

