package com.um.cloudfixum.cloudfixum.util;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class jwTokenUtilities {

    private String SECRET_KEY = "secret";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(CustomUserService customUserService){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,customUserService.loadUserByUsername())
    }

    public String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.ES256,SECRET_KEY).compact();
    }
}
