package com.alkemy.ong.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties")
public class SecurityConfigTest {

    private static String JWT_SECRET = "C9D0629998B86CE363D1885709757E9F29297B912F66BE3A019BDD74C15D3F03";


    private static Long JWT_EXPIRATION_TIME =3600000L;

    public final static String BEARER_TOKEN = "Bearer %s";


    public static String generateToken(String userName, String role) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);

        String authorities = grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String token = Jwts.builder()
                .setSubject(userName)
                .claim("roles", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();

        return String.format(BEARER_TOKEN,token);

    }
}