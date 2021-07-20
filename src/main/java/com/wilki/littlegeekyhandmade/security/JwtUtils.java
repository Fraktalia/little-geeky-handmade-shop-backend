package com.wilki.littlegeekyhandmade.security;

import com.wilki.littlegeekyhandmade.user.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

    private static final long JWT_EXPIRATION_MS = 300_000;
    private static final String JWT_SECRET = "Basikoland2021";
    private static final String BEARER_SCHEMA_NAME = "Bearer ";


    public String generateJwtToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(new Date().toInstant().plusMillis(JWT_EXPIRATION_MS)))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Optional<String> getTokenFromHeader(String authorizationHeaderValue) {
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith(BEARER_SCHEMA_NAME)){
            return Optional.of(authorizationHeaderValue.substring(BEARER_SCHEMA_NAME.length()));
        }

        return Optional.empty();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
