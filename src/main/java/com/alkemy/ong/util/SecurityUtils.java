package com.alkemy.ong.util;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.function.Function;


@Service
public class SecurityUtils {

    public final static String ROLE_PREFIX = "ROLE_";
    public final static String AUTH_HEADER = "authorization";
    public final static String AUTH_TOKEN_TYPE = "Bearer ";
    public final static String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";
    private static final String EMPTY = "";

    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

    public static SimpleGrantedAuthority convertToAuthority(String role){
        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(formattedRole);
    }

    public static String extractAuthTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    public String extractUsername(String authorizationHeader) {
        return extractClaim(getToken(authorizationHeader), Claims::getSubject);
    }

    private String getToken(String authorizationHeader) {

        return authorizationHeader.replace(AUTH_TOKEN_TYPE, EMPTY);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        System.out.println(claimsResolver.apply(claims));
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(base64SecretBytes)
                .parseClaimsJws(token)
                .getBody();
    }
	
}
