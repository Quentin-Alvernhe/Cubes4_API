package com.cubes4.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {
    @Value("${secret}")
    String secret;

    public String generateJwt(MyUserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().toArray()[0].toString());
        claims.put("id", userDetails.getId());
        claims.put("lastname", userDetails.getLastname());
        claims.put("firstname", userDetails.getFirstname());

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .addClaims(claims)
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String getSubject(String jwt) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}