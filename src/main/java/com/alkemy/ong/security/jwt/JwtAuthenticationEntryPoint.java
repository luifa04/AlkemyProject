package com.alkemy.ong.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private MessageSource messageSource;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String tokenExpired = messageSource.getMessage("security.tokenExpired", null, Locale.US);
        String wrongSignature = messageSource.getMessage("security.wrongSignature", null, Locale.US);
        String invalidLogin = messageSource.getMessage("security.invalidLogin", null, Locale.US);

        final String expired = (String) request.getAttribute("expired");
        final String signature = (String) request.getAttribute("signature");
        if (expired != null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, tokenExpired);
        } else if (signature != null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, wrongSignature);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, invalidLogin);
        }
    }
}

